package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.demo.model.StockEntity;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CrawlingService {
    private static final String StockUrlforCookies = "https://finance.naver.com/sise/field_submit.naver?menu=market_sum&returnUrl=http%3A%2F%2Ffinance.naver.com%2Fsise%2Fsise_market_sum.naver&fieldIds=quant&fieldIds=market_sum&fieldIds=open_val&fieldIds=prev_quant&fieldIds=high_val&fieldIds=low_val";
    private static final String NaverStockUrl = "https://finance.naver.com/sise/sise_market_sum.naver?sosok=0&page=";
    public String StockData() {
        List<StockEntity> datalist = new ArrayList<>();
        try {
            // 네이버 시가총액
            Connection.Response response = Jsoup.connect(StockUrlforCookies).method(Method.GET).execute();
            Map<String,String> cookies = response.cookies();

            Document page = response.parse();
            Elements pagenavi = page.select("table.Nnavi td.pgRR");
            int lastpage = Integer.parseInt(pagenavi.select("a").attr("href").substring(34));// 코스피 34, 코스닥 41

            String tmp = pagenavi.select("a").attr("href")+"\n";
            
            for(int i=1;i<=lastpage; i++) {

                response = Jsoup.connect(NaverStockUrl + i).cookies(cookies).method(Method.GET).execute();
                Document doc = response.parse();
                
                Elements rows = doc.select("div.box_type_l table.type_2 tbody tr");

                int count = 0;
                for(Element row : rows) {
                    if(count < 2) {
                        count ++;
                        continue;
                    }
                    Elements stockinfo = row.select("td");
                    if(stockinfo.first().text().isEmpty()) continue;
                    /*
                    id
                    name
                    endprice
                    fprice
                    frate
                    액면가?
                    거래량
                    전일거래량
                    startprice
                    highprice
                    lowprice
                    시가총액
                    */
                    StockEntity stockEntity = new StockEntity();
                    
                    //id
                    stockEntity.setId(Integer.parseInt(stockinfo.get(0).text()));
                    //stockcode
                    String code = stockinfo.get(1).select("a").attr("href").substring(22);
                    stockEntity.setStockcode(code);
                    //stockname
                    stockEntity.setStockname(stockinfo.get(1).text());
                    //stockidx
                    stockEntity.setStockidx(0);
                    //stockdate
                    stockEntity.setStockdate(LocalDate.now().toString());
                    //endprice
                    stockEntity.setEndprice(Integer.parseInt(stockinfo.get(2).text().replaceAll("\\,","").trim()));
                    //fprice
                    stockEntity.setFprice(Integer.parseInt(stockinfo.get(3).text().replaceAll("\\,","").trim()));
                    //frate
                    stockEntity.setFrate(stockinfo.get(4).text());
                    //startprice
                    stockEntity.setStartprice(Integer.parseInt(stockinfo.get(8).text().replaceAll("\\,","").trim()));
                    //highprice
                    stockEntity.setHighprice(Integer.parseInt(stockinfo.get(9).text().replaceAll("\\,","").trim()));
                    //lowprice
                    stockEntity.setLowprice(Integer.parseInt(stockinfo.get(10).text().replaceAll("\\,","").trim()));
                    
                    datalist.add(stockEntity);
                    tmp += stockEntity + "\n";
                }
            }
            return tmp;

        }
        catch(Exception e) {
            return e.getMessage();
        }
    }
}
