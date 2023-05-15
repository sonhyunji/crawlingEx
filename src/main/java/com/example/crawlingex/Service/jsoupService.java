package com.example.crawlingex.Service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class jsoupService {

    public void searchPlant(String searchKeyword) throws IOException {
        String searchName = searchKeyword; //요청받은 검색어

        String flowerPlusUrl = "https://www.flowerseed-mall.com/";
        String flowerUrl = "https://www.flowerseed-mall.com/shop/shopbrand.html?search=";
        Document flowerDoc = Jsoup.connect(flowerUrl + searchName + "&refer=https:").get();
        // 원하는 태그 선택
        Elements products = flowerDoc.select("li.item_list.item_list2");

        // 각 제품에 대한 정보 추출 및 저장
        for (Element product : products) {
            String price = product.select("div.info > p.prdprice > span").text();
            String storeTitle = product.select("div.info > p.prdname.clear_fix ").text();
            String storeLink = flowerPlusUrl + product.select("div.tumb > a").attr("href");
            String imgUrl = flowerPlusUrl + product.select("div.tumb > a > img").attr("src");
            String storeName = "꽃씨몰";

            saveProductData(storeTitle, storeName, price, storeLink, imgUrl, searchName);

            System.out.println("{storeName : " + storeName + "} {storeTitle : " + storeTitle +
                    "} {price : " + price + "} {imgUrl : " + imgUrl + "} {saleLink : " + storeLink + "}");
        }

        System.out.println("------------------------------------------------------------------------------------------------");

        String xplantPlusUrl = "https://www.xplant.co.kr/";
        String xplantUrl = "https://www.xplant.co.kr/shop/search.php?search_str=";
        String xplantImg = "https://webp2.xplant.co.kr/data/thumb/item/170x160-2/";
        Document xplantDoc = Jsoup.connect(xplantUrl + searchName).get();
        // 원하는 태그 선택
        products = xplantDoc.select("ul.itemTd.modified_to_div");

        // 각 제품에 대한 정보 추출 및 저장
        for (Element product : products) {
            String storeTitle = product.select("div.textEllipsis > li > p > a").attr("title");
            String price = product.select("div.textEllipsis > li > p > span.amount").text();
            if(price.contains("₩")) {
                price = price.replaceAll("₩", "");
            } else {
                price = product.select("div.textEllipsis > li > p > strike").text().replaceAll("₩", "");
            }
            String storeLink = xplantPlusUrl + product.select("div.textEllipsis > li > p > a").attr("href");
            String imgUrl = xplantImg + product.select("div.textEllipsis > li > p > a > img").attr("id");
            String storeName = "엑스플랜트";

            saveProductData(storeTitle, storeName, price, storeLink, imgUrl, searchName);

            System.out.println("{storeName : " + storeName + "} {storeTitle : " + storeTitle +
                    "} {price : " + price + "} {imgUrl : " + imgUrl + "} {saleLink : "
                    + storeLink + "}");
        }

        System.out.println("------------------------------------------------------------------------------------------------");

        String simpolUrl = "https://www.simpol.co.kr/front/productsearch.php?s_from=top&s_check=prodname&search=";
        String simpolPlusUrl = "https://www.simpol.co.kr/front/";
        Document simpolDoc = Jsoup.connect(simpolUrl + searchName).get();
        // 원하는 태그 선택
        products = simpolDoc.select("li#lotteTitle0");

        // 각 제품에 대한 정보 추출 및 저장
        for (Element product : products) {
            String storeTitle = product.select("div.goods_name.txt_limit > a").text();
            String price = product.select("div.goods_price > strong").text().replaceAll("원", "");
            String storeLink = simpolPlusUrl + product.select("div.goods_name.txt_limit > a").attr("href");
            String imgUrl = simpolPlusUrl + product.select("div.img > a >  img").attr("src");
            String storeName = product.select("div.seller > a").text();

            saveProductData(storeTitle, storeName, price, storeLink, imgUrl, searchName);

            System.out.println("{storeName : " + storeName + "} {storeTitle : " + storeTitle +
                    "} {price : " + price + "} {imgUrl : " + imgUrl + "} {saleLink : "
                    + storeLink + "}");
        }

    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveProductData(String storeTitle, String storeName, String price, String storeLink, String imgUrl, String searchName) {
        String sql = "INSERT INTO product (storeTitle, storeName, price, storeLink, imgUrl, searchName) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, storeTitle, storeName, price, storeLink, imgUrl, searchName);
    }

}
