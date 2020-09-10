package com.github.Pewbe;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.awt.*;

public class PlantHumanMain {
    public static void main(String[] args) {
        String token = "NzUxODI1MTAzMjE3NzU0MTIy.X1OtsA.mda-6Z_B-MK3ABJLDinRekoyFm4";
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
                        String target = msg.replace("식물인간 구글검색 ", "");
                        String address = "https://www.google.com/search?q=" + target + "&sxsrf=ALeKk01E1xQOkydUupKtjyiL2IefoKBzjQ:1599377060465&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiSgq_s_9PrAhVEFqYKHdsWDqoQ_AUoAXoECBcQAw&cshid=1599377067782039&biw=1536&bih=775";
                        Document doc = Jsoup.connect(address).header("User-Agent", "Chrome/19.0.1.84.52").get();
                        String image = doc.select("img").text();
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
                else if( msg.contains("프사") ){
                    try {
                        String replaced = msg.replace("식물인간 프사 ", "");

                        embed.setColor( Color.GREEN );
                        embed.setImage( ev.getApi().getUserById( replaced.replaceAll("\\W", "") ).get().getAvatar() );
                        embed.setFooter("이거 맞겠지.....?");

                        ev.getChannel().sendMessage( embed );
                    } catch ( Exception e ){
                        e.printStackTrace();
                        ev.getChannel().sendMessage("어라..? 제대로 된 명령어가 아닌가본데.....?");
                    }
                }
                else if( msg.contains("사랑해") )
                    ev.getChannel().sendMessage("헤에.....에...엣!?");
                else if( msg.contains("안녕") )
                    ev.getChannel().sendMessage("안ㄴ....앗..이..인간..!?");
                else if ( msg.contains("도움말") ) {
                    embed.setColor( Color.GREEN );
                    embed.setTitle("식물인간 사용법");
                    embed.setDescription("식물인간은 자잘한 기능들을 사용할 수 있는 기능성 봇입니다.\n종족은 아마도 나무입니다.");
                    embed.addField("`식물인간 기능`", "자잘한 기능들에 대한 도움말을 보여줍니다.");
                    embed.addField("`식물인간 게임`", "별거 없는 게임에 대한 도움말을 보여줍니다.");

                    ev.getChannel().sendMessage( embed );
                }
                else{
                    ev.getChannel().sendMessage("에..어디보자..으음....그런 명령어는 없는걸....?");
                }
            }
        });

        api.addServerJoinListener( ev -> {
        } );
    }
}