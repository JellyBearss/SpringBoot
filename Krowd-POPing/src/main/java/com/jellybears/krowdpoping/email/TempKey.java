package com.jellybears.krowdpoping.email;

import java.util.Random;

public class TempKey {
        private boolean lowerCheck; //소문자로 변환 여부
        private int size; // 생성될 임시 키의 길이

        public String getKey(boolean lowerCheck, int size) {
            this.lowerCheck = lowerCheck;
            this.size = size;
            return init();
        }
        private String init(){
            Random ran = new Random();
            StringBuffer sb = new StringBuffer();
            int num = 0;
            do{
                num = ran.nextInt(75)+48;
                if((num >= 48 && num <= 57)||(num >= 65 && num <= 90)||(num >= 97 && num <= 122)){
                    sb.append((char)num);

                } else {
                    continue;
                }
            }while (sb.length() < size);
            if(lowerCheck){
                return sb.toString().toLowerCase();
            } return sb.toString();
        }



}
