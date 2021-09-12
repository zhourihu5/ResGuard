
package com.dongnao.res;

import com.dongnao.res.obfuscate.Obfuscater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiang
 * @date 2018/2/3
 */
public class Main {


    public static final String PATH = "res_guard_core/build/andres";

    static List<String> read7ZipConfig() throws Exception {
        ArrayList<String> sevenZips = new ArrayList<>();
        File sevenZipConfigFile = new File("res_guard_core/config/7zconfig.txt");
        FileInputStream fis = new FileInputStream(sevenZipConfigFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        String line;
        while ((line = reader.readLine()) != null) {
            sevenZips.add(line);
        }
        reader.close();
        return sevenZips;
    }

    public static void main(String[] args) throws Exception {
        //获得需要强制使用7zip极限压缩的配置
        List<String> sevenZips = read7ZipConfig();

        //
        File apkFile = new File("G:\\tengxun\\LQRWeChat\\app\\build\\outputs\\apk\\debug\\app-debug" +
                ".apk");
        Obfuscater obfuscater = new Obfuscater(sevenZips, apkFile);
        //混淆会产生apk
        obfuscater.obfuscate();

//        int[] i = new int[2];
//        i[0] = 0;
//        i[1] = 5;
//        //字符串数据
//        //utf8【编码长度】【字符串长度】【字符串数据】
//        byte[] data = new byte[10];
//        //第一个字符串 data[0]-date[x]
//        //第二个字符串 data[5]-data[x]

        System.out.println("===================================================================");
        for (String s : obfuscater.mTypeMap.keySet()) {
            if (obfuscater.mTypeMap.get(s) != null) {
                System.out.println(s + " ===> " + obfuscater.mTypeMap.get(s));
            }
        }
        System.out.println("===================================================================");
        for (String s : obfuscater.mSpecMap.keySet()) {
            if (obfuscater.mSpecMap.get(s) != null) {
                System.out.println(s + " ===> " + obfuscater.mSpecMap.get(s));
            }
        }
    }
}
