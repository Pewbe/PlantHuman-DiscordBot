package com.github.Pewbe;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PlantHumanMain {
    public static void main(String[] args) {
        String token = "NzUxODI1MTAzMjE3NzU0MTIy.X1OtsA.cyWscVi8d9IcWyf0UnABoJmz0hE";
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

        System.out.println("디스코드 로그인에 성공했어...!");
        System.out.println("서버 초대 링크는 여기야...: " + api.createBotInvite());

        api.addMessageCreateListener(ev -> {
            EmbedBuilder embed = new EmbedBuilder();
            String msg = ev.getMessage().getContent();

            if( ev.getMessage().getContent().startsWith("식물인간") ){
                Channel ch = ev.getChannel();

                if( msg.contains("구글검색") ){
                    try {
                        /*단어검색(이었던것)
                        String target = msg.replace("식물인간 단어검색 ", "");
                        String address = "https://dict.naver.com/search.nhn?dicQuery=" + target + "&query=" + target + "&target=dic&ie=utf8&query_utf=&isOnlyViewEE=";
                        Document doc = Jsoup.connect(address).header("User-Agent", "Chrome/19.0.1.84.52").get();

                        String one = doc.select("tr").eq(324).text();
                        ev.getChannel().sendMessage( one + " 이 정도 가져왔어...!" );
                        
                        Document doc = Jsoup.connect("https://www.findip.kr/").header("User-Agent", "Chrome/19.0.1.84.52").get();

                        String h1 = doc.select("h1").text();
                        String h2 = doc.select("h2").eq(0).text();

                        ev.getChannel().sendMessage(h1 + "\n" + h2 + "\n이 정도의 내용을 가져왔어..!");
                         */
                    } catch ( Exception e ) { e.printStackTrace(); }
                }
                else if( msg.contains("안녕") )
                    ev.getChannel().sendMessage("안ㄴ....앗..이..인간..!?");
                else if ( msg.contains("도움말") ) {
                    embed.setTitle("식물인간 사용법");
                }
                else{
                    ev.getChannel().sendMessage("에..어디보자..으음....그런 명령어는 없는걸....?");
                }
            }
        });
    }
}