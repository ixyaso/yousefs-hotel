/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication3;

/**
 *
 * @author youse
 */
public class roomData {
    private Integer roomNum;
    private String roomType;
    private String status;
    private Double price;
    
    public roomData(Integer roomNum, String roomType, String status, Double price)
    {
        this.roomNum = roomNum;
        this.roomType = roomType;
        this.status = status;
        this.price = price;

    }
    
    public Integer getRoom(){
        return roomNum;
    }
    public String getRoomType()
    {
        return roomType;
    }
    public String getStatus()
    {
        return status;
    }
    public Double getPrice()
    {
        return price;
    }
    
}
