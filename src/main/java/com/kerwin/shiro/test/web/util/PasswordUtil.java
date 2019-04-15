package com.kerwin.shiro.test.web.util;

import java.util.Date;
import java.util.Random;

/**
 * @ClassName: PasswordUtil
 * @Description:
 * @version: v1.0.0
 * @Author: d.wang
 * @Date: 2019-04-15 9:48
 */
public class PasswordUtil
{
    public static final String[] word = {
            "a","b","c","d","e","f","g",
            "h","j","k","m","n",
            "p","q","r","s","t",
            "u","v","w","x","y","x",
            "A","B","C","D","E","F","G",
            "H","J","K","M","N",
            "P","Q","R","S","T",
            "U","V","W","X","Y","X"
    };

    public static final String[] num = {
            "2","3","4","5","6","7","8","9"
    };

    public static String generatePassword(){

        StringBuffer stringBuffer = new StringBuffer();

        Random random = new Random(new Date().getTime());

        boolean flag = false;
        int length = random.nextInt(3) + 8;
        for (int i = 0; i < length; i++)
        {
            if (flag){
                stringBuffer.append(num[random.nextInt(num.length)]);
            }else {
                stringBuffer.append(word[random.nextInt(word.length)]);
            }
            flag = !flag;
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) throws InterruptedException
    {
        System.out.println(PasswordUtil.generatePassword());
        System.out.println(MD5Util.encrypt(PasswordUtil.generatePassword()));
        Thread.sleep(100);
        System.out.println(PasswordUtil.generatePassword());
        Thread.sleep(100);
        System.out.println(PasswordUtil.generatePassword());
    }
}
