package cn.edu.neu.School_Jobs.model;

import javax.persistence.*;

@Table(name = "fund_manager")
public class FundManager {
    @Id
    @Column(name = "manager_id")
    private Integer managerId;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "manager_products")
    private String managerProducts;

    @Column(name = "show_me")
    private String showMe;

    /**
     * @return manager_id
     */
    public Integer getManagerId() {
        return managerId;
    }

    /**
     * @param managerId
     */
    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    /**
     * @return manager_name
     */
    public String getManagerName() {
        return managerName;
    }

    /**
     * @param managerName
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    /**
     * @return manager_products
     */
    public String getManagerProducts() {
        return managerProducts;
    }

    /**
     * @param managerProducts
     */
    public void setManagerProducts(String managerProducts) {
        this.managerProducts = managerProducts;
    }

    /**
     * @return show_me
     */
    public String getShowMe() {
        return showMe;
    }

    /**
     * @param showMe
     */
    public void setShowMe(String showMe) {
        this.showMe = showMe;
    }
}