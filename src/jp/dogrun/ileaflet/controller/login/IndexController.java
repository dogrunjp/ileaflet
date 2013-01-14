package jp.dogrun.ileaflet.controller.login;

import jp.dogrun.ileaflet.model.Actor;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.StringUtil;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {

        Actor actor = sessionScope(Actor.class.getName());
        //ログイン状態をダッシュボードではなく、リダイレクトする
        if ( actor != null ) {

            //TODO だっしゅ

            return redirect("../dashboard/");
        }
       
        //コントローラー必要なしのタイプの場合
        String type = requestScope("type");
        if ( !StringUtil.isEmpty(type) ) {
            return forward(type + ".jsp");
        }

        //リダイレクトページのリクエスト値を取得
        //設定

        //ログインページに移動
        return forward("index.jsp");
    }
}
