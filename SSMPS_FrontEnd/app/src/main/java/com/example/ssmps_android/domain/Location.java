package com.example.ssmps_android.domain;


import android.graphics.Rect;
import android.util.Log;

import java.io.Serializable;
import java.util.List;

public class Location implements Serializable {
    private List<Item> itemList;
    private float startX;
    private float startY;
    private float endX;
    private float endY;

    private float centerX;
    private float centerY;

    public Location(float startX, float startY, float endX, float endY) {
        this.startX = Math.round(startX);
        this.startY = Math.round(startY);
        this.endX = Math.round(endX);
        this.endY = Math.round(endY);
        centerX = startX + ((endX - startX) / 2);
        centerY = startY + ((endY - startY) / 2);
    }

    public boolean intersect(Location location){
        if (this.startX < location.getEndX() && location.getStartX() < this.endX
                && this.startY < location.getEndY() && location.getStartY() < this.endY){
            if(this.startX < location.getEndX()){
                Log.e("startX", "c");
                location.setEndX(this.startX - 2);
                location.setStartX(location.getStartX() - 2);
            }
            if(location.getStartX() < this.endX){
                Log.e("loc startX", "c");
                location.setStartX(this.endX + 2);
                location.setEndX(location.getEndX() + 2);
            }
            if(this.startY < location.getEndY()){
                Log.e("startY", "c");
                location.setEndY(this.startY - 2);
                location.setStartY(location.getStartY() - 2);
            }
            if(location.startY < this.endY){
                Log.e("loc startY", "c");
                location.setStartY(this.endY + 2);
                location.setEndY(location.getEndY() + 2);
            }
            return true;
        }
        return false;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = Math.round(startX);
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = Math.round(startY);
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = Math.round(endX);
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY) {
        this.endY = Math.round(endY);
    }

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }
}