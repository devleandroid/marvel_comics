package marvel.br.com.lebronx.marvelcomics.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import marvel.br.com.lebronx.marvelcomics.Model.MarvelCharacter;
import marvel.br.com.lebronx.marvelcomics.R;

public class CharacterListAdapter extends BaseAdapter {

    private List<MarvelCharacter> data;
    private final JSONArray jsonArray;
    private final Activity activity;

    public CharacterListAdapter(Activity activity, JSONArray jsonArray) {
        this.activity = activity;
        this.jsonArray = jsonArray;
    }

    @Override
    public int getCount() {
        if(null==jsonArray) return  0;
        return jsonArray.length();
    }

    @Override
    public Object getItem(int position) {
        if(null==jsonArray) return null;

        JSONObject x = null;
        try {
            x = jsonArray.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return x;
    }

    @Override
    public long getItemId(int position) {
        JSONObject jsonObject = (JSONObject) getItem(position);
        return jsonObject.optLong("id");
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.list_item_template,null);
        }

        TextView tvtitle = (TextView) view.findViewById(R.id.tvtitle);
        TextView tvdesc = (TextView) view.findViewById(R.id.tvdesc);
        TextView tvplatform = (TextView) view.findViewById(R.id.tvplateform);
        ImageView imgView = (ImageView) view.findViewById(R.id.gameImage);

        JSONObject jsondata = (JSONObject) getItem(position);

        String imgurl = "http://demo.yasirameen.com/uploads/";

        try {

            tvtitle.setText(jsondata.getString("title"));
            tvdesc.setText(jsondata.getString("desc"));
            tvplatform.setText(jsondata.getString("platforms"));

            String itemImage = jsondata.getString("image");
            Picasso.with(activity.getApplicationContext())
                    .load(imgurl+itemImage)
                    .into(imgView);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}
