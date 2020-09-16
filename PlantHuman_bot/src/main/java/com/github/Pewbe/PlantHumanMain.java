package com.github.Pewbe;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.awt.*;

public class PlantHumanMain {
    public static void main(String[] args) {
        String token = "토오큰";
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

        System.out.println("디스코드 로그인에 성공했어...!");
        System.out.println("서버 초대 링크는 여기야...: " + api.createBotInvite());

        api.addMessageCreateListener(ev -> {
            EmbedBuilder embed = new EmbedBuilder();
            String msg = ev.getMessage().getContent();
            Color c = new Color( 156, 199, 95 );

            embed.setColor( c );

            if( ev.getMessage().getContent().startsWith("식물인간") ){
                Channel ch = ev.getChannel();
                String userMention = "<@" + ev.getMessage().getAuthor().getId() + ">";

                if( msg.contains("구글검색") ){
                    try {
                        String target = msg.replace("식물인간 구글검색 ", "");
                        String address = "https://www.google.com/search?q=" + target + "&sxsrf=ALeKk01E1xQOkydUupKtjyiL2IefoKBzjQ:1599377060465&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiSgq_s_9PrAhVEFqYKHdsWDqoQ_AUoAXoECBcQAw&cshid=1599377067782039&biw=1536&bih=775";
                        Document doc = Jsoup.connect(address).header("User-Agent", "Chrome/19.0.1.84.52").get();
                        String image = doc.select("img").eq(15).text();

                        embed.setImage( image );

                        ev.getChannel().sendMessage( userMention );
                        ev.getChannel().sendMessage( embed );

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

                        embed.setImage( ev.getApi().getUserById( replaced.replaceAll("\\W", "") ).get().getAvatar() );
                        embed.setFooter("이거 맞겠지.....?");

                        ev.getChannel().sendMessage( userMention );
                        ev.getChannel().sendMessage( embed );
                    } catch ( Exception e ){
                        e.printStackTrace();
                        ev.getChannel().sendMessage("어라..? 제대로 된 명령어가 아닌가본데.......");
                    }
                }
                else if( msg.contains("사랑해") )
                    ev.getChannel().sendMessage("헤에.....에...엣!?");
                else if( msg.contains("안녕") )
                    ev.getChannel().sendMessage("ㅇ...아...안녕......?");
                else if ( msg.contains("도움말") ) {
                    String replaced = msg.replace( "식물인간 도움말 ", "" );

                    if( replaced.equals("기능") ){
                        embed.setTitle("기능");
                    }
                    else if (replaced.equals("나무")){
                        embed.setTitle("나무심기");
                        embed.setDescription("나무심기 게임에 관한 설명입니다.\n처음이라면 `식물인간 등록` 을 입력하면\n게임을 시작하는 방법을 알려드립니다.");
                        embed.addField("`- 식물인간 나무 가방`", "현재 자신의 소지품 목록을 보여줍니다.");
                        embed.addField("`- 식물인간 나무 지갑`", "현재 자신의 소지금과 소지금 랭킹을 보여줍니다.");
                        embed.addField("`- 식물인간 나무 현황`", "현재 자신이 심은 나무의 현황을 보여주고 관리합니다.");
                        embed.addField("`- 식물인간 나무 심기`", "보유한 씨앗을 심습니다. 나무 한 그루는 자리 1을 차지합니다.");
                        embed.addField("`- 식물인간 나무 베기`", "다 자란 나무를 벱니다. 벤 나무는 목재가 되고 사라집니다.\n~~식물인간이 슬퍼합니다.~~");
                        embed.addField("`- 식물인간 나무 제작`", "보유한 목재로 물건을 만듭니다.\n만든 물건은 가치가 매겨져 팔 수 있습니다.");
                        embed.addField("`- 식물인간 나무 팔기`", "만든 물건을 팝니다.\n물건을 팔아서 돈을 얻습니다.");
                        embed.addField("`- 식물인간 나무 상점`", "상점 목록을 보여줍니다. 씨앗이나 도구를 구매할 수 있습니다.");
                    }
                    else {
                        embed.setTitle("식물인간 도움말");
                        embed.setDescription("식물인간은 자잘한 기능들을 사용할 수 있는 기능성 봇입니다.\n종족은 아마도 나무입니다.");
                        embed.addField("`- 식물인간 도움말 기능`", "자잘한 기능들에 대한 도움말을 보여줍니다.");
                        embed.addField("`- 식물인간 도움말 나무`", "나무심기 게임에 대한 도움말을 보여줍니다.");
                    }

                    ev.getChannel().sendMessage( userMention );
                    ev.getChannel().sendMessage( embed );
                }
                else if( msg.contains("나무") ){
                    plantingTree( ev );
                }
                else if( msg.endsWith("등록") ){
                    embed.setTitle("나무심기 등록");
                    embed.setDescription("`식물인간 등록 [닉네임]` 으로 등록을 진행해주세요.\n`[닉네임]`에 지정한 이름은 식물인간 봇의 게임 내에서만 이용됩니다.");

                    ev.getChannel().sendMessage( embed );
                }
                else{
                    ev.getChannel().sendMessage("에..어디보자..으음....그런 명령어는 없는걸....?");
                }
            }
        });
    }

    private static void plantingTree( MessageCreateEvent ev ) {

    }
}