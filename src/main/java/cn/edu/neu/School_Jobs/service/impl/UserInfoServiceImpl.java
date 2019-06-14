package cn.edu.neu.School_Jobs.service.impl;

import cn.edu.neu.School_Jobs.mapper.UserInfoMapper;
import cn.edu.neu.School_Jobs.model.BuyOrder;
import cn.edu.neu.School_Jobs.model.HistoricalFund;
import cn.edu.neu.School_Jobs.model.SellOrder;
import cn.edu.neu.School_Jobs.model.UserInfo;
import cn.edu.neu.School_Jobs.service.BuyOrderService;
import cn.edu.neu.School_Jobs.service.HistoricalFundService;
import cn.edu.neu.School_Jobs.service.SellOrderService;
import cn.edu.neu.School_Jobs.service.UserInfoService;
import cn.edu.neu.School_Jobs.util.AbstractService;
import cn.edu.neu.School_Jobs.util.Encryptor;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * created by fzb on 2019/05/10.
 */
@Service
public class UserInfoServiceImpl extends AbstractService<UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    BuyOrderService buyOrderService;
    @Autowired
    SellOrderService sellOrderService;
    @Autowired
    HistoricalFundService historicalFundService;

    @Override
    public float getHistoryRate(int userId, int day) {
        // 该用户 X天内 所有购买且已经确认的订单
        List<BuyOrder> buyOrders = buyOrderService.findHistoryOrder(day, userId, true);
        // 该客户在X天内 所有卖出的订单
        List<SellOrder> sellOrders = sellOrderService.findHistoryOrder(day, userId, "1",null);
        // X天内买的订单交易的总金额
        float amount = 0;
        // 现在基金剩余的价值
        float remain_money = 0;
        // 已经卖出的订单的净值*份额
        float has_sell_money = 0;
        // 遍历所有的买入订单
        for (BuyOrder buyOrder : buyOrders) {
            // 对每个购买订单的交易金额进行累加
            amount += buyOrder.getTransactionAmount();
            // 查询基金的历史净值订单
            HistoricalFund historicalFund = historicalFundService.findById(buyOrder.getFundId());
            // 对历史净值进行切割
            String[] prices = historicalFund.getHistoryPrice().split("-");
            // 取出它的最新净值
            float net = Float.parseFloat(prices[prices.length - 1]);
            // 计算该客户现在拥有的单个基金的价值
            remain_money += net * buyOrder.getResidualShare();
        }
        //遍历所有卖出且已经订单
        for (SellOrder sellOrder : sellOrders) {
            // 已经卖出的订单
//            if (sellOrder.getConfirmSign()) {
                has_sell_money += sellOrder.getSellShare() * sellOrder.getSureNet() - sellOrder.getServiceCharge();
//            }//对未卖出的订单进行基金剩余价值的重新计算
//            else {
//                // 取出该基金的净值价格表
//                String[] prices = historicalFundService.findById(sellOrder.getFundId()).getHistoryPrice().split("-");
//                // 将该基金的最新价值*份额加入到现在拥有的财富中
//                remain_money += sellOrder.getSellShare() * Float.parseFloat(prices[prices.length - 1]);
//            }
        }
        // 收益率公式为 （目前拥有的基金+已经卖出的基金）的总价格/总共已经花的总价格
        float getRate = (remain_money + has_sell_money) / amount;
        // 这里注意除数不可以为0，故NULL值的话判定为1
        if (amount == 0) {
            getRate = 1.f;
        }
        //返回基金公式
        return getRate;
    }

    @Override
    public String getEncryPhotoUrl(int userId) {
        String nomalPhotoUrl = String.valueOf(userId);
        return Encryptor.encrypt(nomalPhotoUrl, nomalPhotoUrl) + ".jpg";
    }

    @Override
    public int computeAge(String IdNO){
        int leh = IdNO.length();
        String dates="";
        if (leh == 18) {
            dates = IdNO.substring(6, 10);
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year=df.format(new Date());
            int u=Integer.parseInt(year)-Integer.parseInt(dates);
            return u;
        }else{
            dates = IdNO.substring(6, 8);
            return Integer.parseInt(dates);
        }
    }
    @Override
    public String computeSex(String IdNO){
        StringBuffer sb = new StringBuffer(IdNO);
        return String.valueOf(Integer.parseInt(String.valueOf(sb.charAt(sb.length()-2)))%2);
    }

    @Override
    public String getEncryPayPassword(String payPassword){
        return Encryptor.encrypt(payPassword,payPassword);
    }
    @Override
    public int selectByIdAndPayPassword(String userId,String payPassword){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",userId);
        jsonObject.put("payPassword",payPassword);
        return userInfoMapper.selectByIdAndPayPassword(jsonObject);
    }
    @Override
    public UserInfo anonymousUserInfo(UserInfo userInfo){
//        String identityCard = userInfo.getIdentityCard();
//        String anonymousIdentity = "";
//        if(identityCard.length()==18){
//            anonymousIdentity = "**************"+identityCard.substring(14,18);
//        }
//        userInfo.setIdentityCard(anonymousIdentity);
//        if(userInfo.getName()!=null){
//            userInfo.setName(userInfo.getName().substring(0,1)+"**");
//        }
        userInfo.setPayPassword(null);
        return userInfo;
    }

    @Override
    public List<UserInfo> findFriendByUserId(String userId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        return userInfoMapper.findFriendByUserId(jsonObject);
    }

    @Override
    public List<UserInfo> findIsAddingFriend(String userId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        return userInfoMapper.findIsAddingFriend(jsonObject);
    }
}
