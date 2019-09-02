package io.github.baamu.baamubackend.resources;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author oshan
 */
public class Download implements Runnable {
    private File file = null;
    private String link = "";
    private URL url = null;

    private double downloadedSize = 0;
    private double fileSize = 0;

    String userId = "";

    public Download(String link, String userId) {
        this.link = link;
        try {
            url = new URL(link);
            String[] urlData = url.getFile().split("/");
            file = new File("C:\\Users\\oshan\\Desktop\\"+urlData[urlData.length-1]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            fileSize = http.getContentLength(); //Bytes
            System.out.println("File : "+file.getName());
            System.out.println("File Size "+ fileSize +"KB");

            System.out.println("Headers ");
            System.out.println("/////////////////////////");

            for(Map.Entry<String, List<String>> header : http.getHeaderFields().entrySet()) {
                System.out.print(header.getKey() + " : ");
                for (String s : header.getValue()) {
                    System.out.print(s + ", ");
                }
                System.out.println();
            }

            System.out.println("///////////////////////////");

            BufferedInputStream inputStream = new BufferedInputStream(http.getInputStream());
            FileOutputStream fout = new FileOutputStream(file);
            BufferedOutputStream bout = new BufferedOutputStream(fout,1024*1024);
            byte[] buffer = new byte[1024*1024];
            int read=0;
            int readSize = 0;

            while ((read = inputStream.read(buffer,0,1024*1024)) != -1) {
                bout.write(buffer,0,read);
                downloadedSize += read;
                readSize += read;

                if(readSize >= 1024 * 1024)     //flushes data on each 1MB
                    bout.flush();

                double downloadedPercent = (downloadedSize/fileSize) * 100;
                System.out.println(String.format("Downloaded %.2f/%.2f : %.2f%%",downloadedSize,fileSize,downloadedPercent));
            }

            System.out.println("Download completed!");
//            bout.flush();
            bout.close();
            fout.close();
            inputStream.close();
            http.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
