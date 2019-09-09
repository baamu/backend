package io.github.baamu.baamubackend.resources;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author oshan
 */
public class YoutubeDownload implements Runnable{

    String link = "";

    public YoutubeDownload() {

    }

    public YoutubeDownload(String link) {
        this.link = link;
    }

    public String getHTMLLink(String link) {
        HttpURLConnection conn = null;
        StringBuilder contents = new StringBuilder();
        try {
            conn = (HttpURLConnection) new URL(link).openConnection();
//            conn.setConnectTimeout(CONNECT_TIMEOUT);
//            conn.setReadTimeout(READ_TIMEOUT);

            InputStream is = conn.getInputStream();

            String enc = conn.getContentEncoding();

            if (enc == null) {
                Pattern p = Pattern.compile("charset=(.*)");
                Matcher m = p.matcher(conn.getHeaderField("Content-Type"));
                if (m.find()) {
                    enc = m.group(1);
                }
            }

            if (enc == null)
                enc = "UTF-8";

            BufferedReader br = new BufferedReader(new InputStreamReader(is, enc));

            String line = null;


            while ((line = br.readLine()) != null) {
                contents.append(line);
                contents.append("\n");

            }
        }catch (IOException ex) {

        }

        return contents.toString();
    }

    @Override
    public void run() {
        List<String> urlList = new ArrayList<>();
        Pattern urlencod = Pattern.compile("\"url_encoded_fmt_stream_map\":\"([^\"]*)\"");
        Matcher urlencodMatch = urlencod.matcher(getHTMLLink(link));
        if (urlencodMatch.find()) {
            String url_encoded_fmt_stream_map;
            url_encoded_fmt_stream_map = urlencodMatch.group(1);
            Pattern encod = Pattern.compile("url=(.*)");
            Matcher encodMatch = encod.matcher(url_encoded_fmt_stream_map);
            if (encodMatch.find()) {
                String sline = encodMatch.group(1);
                String[] urlStrings = sline.split("url=");
                for (String urlString : urlStrings) {
                    String url = null;
                    urlString = StringEscapeUtils.unescapeJava(urlString);
                    Pattern link2 = Pattern.compile("([^&,]*)[&,]");
                    Matcher linkMatch = link2.matcher(urlString);
                    if (linkMatch.find()) {
                        url = linkMatch.group(1);
                        try {
                            url = URLDecoder.decode(url, "UTF8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    urlList.add(url);
                }
            }
        }
        System.out.println(urlList.size());
        for(String s : urlList) {
            System.out.println(s);
        }

        new Thread(
                new Download(urlList.get(0),"U001")
        ).start();
    }
}
