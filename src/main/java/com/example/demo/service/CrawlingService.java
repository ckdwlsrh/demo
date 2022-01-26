package com.example.demo.service;

import java.time.LocalDate;

import com.example.demo.model.StockEntity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CrawlingService {
    private static final String NaverStockUrl = "https://finance.naver.com/sise/field_submit.naver?menu=quant&returnUrl=http%3A%2F%2Ffinance.naver.com%2Fsise%2Fsise_quant.naver%3Fsosok%3D0&fieldIds=quant&fieldIds=market_sum&fieldIds=open_val&fieldIds=prev_quant&fieldIds=high_val&fieldIds=low_val";
    public String StockData() {
        try {
            // 코스피 거래상위 100
            Document doc = Jsoup.connect(NaverStockUrl).get();
            Elements rows = doc.select("div.box_type_l table tbody tr");
            String tmp = "";
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
                int code = Integer.parseInt(stockinfo.get(1).select("a").attr("href").substring(22));
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
                stockEntity.setStartprice(Integer.parseInt(stockinfo.get(7).text().replaceAll("\\,","").trim()));
                //highprice
                stockEntity.setHighprice(Integer.parseInt(stockinfo.get(8).text().replaceAll("\\,","").trim()));
                //lowprice
                stockEntity.setLowprice(Integer.parseInt(stockinfo.get(9).text().replaceAll("\\,","").trim()));
                
                tmp += stockEntity + "\n";
            }
            return tmp;

        }
        catch(Exception e) {
            return e.getMessage();
        }
    }
}
