package com.gms.model.member;

public class TrainingPackage {

   private int packageId;
   private String packageName;
   private double packagePrice;

   public TrainingPackage (){

   }

   public TrainingPackage(int packageId, String packageName, double packagePrice) {
      this.packageId = packageId;
      this.packageName = packageName;
      this.packagePrice = packagePrice;
   }

   public int getPackageId() {
      return packageId;
   }

   public void setPackageId(int packageId) {
      this.packageId = packageId;
   }

   public String getPackageName() {
      return packageName;
   }

   public void setPackageName(String packageName) {
      this.packageName = packageName;
   }

   public double getPackagePrice() {
      return packagePrice;
   }

   public void setPackagePrice(double packagePrice) {
      this.packagePrice = packagePrice;
   }


}
