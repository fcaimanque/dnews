package cl.ucn.disc.dsm.fcaimanque.dnews.activities;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import org.acra.annotation.AcraToast;

import cl.ucn.disc.dsm.fcaimanque.dnews.adapters.ArticleAdapter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

import cl.ucn.disc.dsm.fcaimanque.dnews.interfaces.NewsController;
import cl.ucn.disc.dsm.fcaimanque.dnews.model.Article;
import cl.ucn.disc.dsm.fcaimanque.dnews.R;

@Slf4j
public class MainActivity extends AppCompatActivity {

    /**
     * Adaptador de articulos.
     */
    private ArticleAdapter articleAdapter;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.articleAdapter = new ArticleAdapter(this);

        ListView lv = findViewById(R.id.am_lv_articles);
        lv.setAdapter(this.articleAdapter);

    }

    /**
     *
     */
    @Override
    protected void onStart() {
        super.onStart();

        // ejecucion en segundo plano.
        AsyncTask.execute(() -> {
            try {
                List<Article> articulos = NewsController.getArticles();
                articleAdapter.add(articulos);

                // ejecucion en primer plano.
                //aqui revisamos errores
                runOnUiThread(()->{
                    articleAdapter.notifyDataSetChanged();
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
