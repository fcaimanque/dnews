package cl.ucn.disc.dsm.fcaimanque.dnews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import cl.ucn.disc.dsm.fcaimanque.dnews.R;
import cl.ucn.disc.dsm.fcaimanque.dnews.model.Article;
import lombok.extern.slf4j.Slf4j;

/**
 * Adaptador del articulo
 */
@Slf4j
public final class ArticleAdapter extends BaseAdapter {

    /**
     * Listado de articulos
     */
    private final List<Article> articles = new ArrayList<>();

    /**
     * Inflador de layouts
     */
    private final LayoutInflater inflater;

    /**
     * Constructor del adaptador
     * @param context necesario para obtener el inflador
     */
    public ArticleAdapter(final Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * Carga listado de articulos en el adaptador
     * @param articles a agregar.
     */
    public void add(final List<Article> articles) {
        this.articles.addAll(articles);
    }

    /**
     * @return numero de articulos en el adaptador.
     */
    @Override
    public int getCount() {
        return this.articles.size();
    }

    /**
     * @param position posicion.
     * @return un articulo en la posicion dad.a
     */
    @Override
    public Article getItem(final int position) {
        return this.articles.get(position);
    }

    /**
     *
     * @param position posicion
     * @return posicion.
     */
    @Override
    public long getItemId(final int position) {
        return position;
    }


    /**
     * TODO: AGREGAR JAVADOC
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {


        ArticleViewHolder holder;

        // Inflar la vista solo si es nula, si no, reutilizarla.
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            holder = new ArticleViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ArticleViewHolder) convertView.getTag();
        }

        // Obtener articulo.
        final Article article = getItem(position);

        // Llenar los textViews con los datos de la persona:
        // Si su dato es "null", no mostrarlo.
       // holder.source.setText(article.getSource());
        holder.author.setText(article.getAuthor());
        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());
        holder.url.setText(article.getUrl());
        holder.urlToImage.setText(article.getUrlToImage());
        holder.publishedAt.setText(article.getPublishedAt());
        holder.content.setText(article.getContent());

        // Devolver la vista con la informacion del articulo.
        return convertView;
    }

    /**
     * Clase interna
     */
    private static class ArticleViewHolder {
        TextView publishedAt;
        TextView content;
        TextView source;
        TextView author;
        TextView title;
        TextView description;
        TextView url;
        TextView urlToImage;

        ArticleViewHolder(final View view) {
            this.source = view.findViewById(R.id.rp_tv_source);
            this.author = view.findViewById(R.id.rp_tv_author);
            this.title = view.findViewById(R.id.rp_tv_title);
            this.description = view.findViewById(R.id.rp_tv_desc);
            this.url = view.findViewById(R.id.rp_tv_url);
            this.urlToImage = view.findViewById(R.id.rp_tv_url2Img);
            this.publishedAt = view.findViewById(R.id.rp_tv_published);
            this.content = view.findViewById(R.id.rp_tv_content);
        }

    }

}
