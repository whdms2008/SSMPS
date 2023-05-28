package com.example.ssmps_paymentscreen;

public class ListViewItem  {
    private String text; // 상품 이름
    private int price; // 상품 가격
    private int cnt; // 상품 갯수

    public void setText(String text){
        this.text = text;
    }
    public void setPrice(int price) {this.price = price;}
    public  void setCnt(int cnt) {this.cnt = cnt;}

    public String getText(){
        return this.text;
    }
    public int getPrice() {return this.price;}
    public int getCnt() {return this.cnt;}
}
