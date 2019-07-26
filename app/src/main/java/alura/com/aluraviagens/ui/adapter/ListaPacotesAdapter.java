package alura.com.aluraviagens.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import alura.com.aluraviagens.R;
import alura.com.aluraviagens.model.Pacote;

import static alura.com.aluraviagens.util.DiasUtil.formataEmTexto;
import static alura.com.aluraviagens.util.MoedaUtil.formataParaBrasileiro;
import static alura.com.aluraviagens.util.ResourceUtil.devolveDrawable;

public class ListaPacotesAdapter extends BaseAdapter {

    private final List<Pacote> pacotes;
    private final Context context;

    public ListaPacotesAdapter(List<Pacote> pacotes, Context context) {
        this.pacotes = pacotes;
        this.context = context;
    }


    @Override
    public int getCount() {
        return pacotes.size();
    }

    @Override
    public Pacote getItem(int posicao) {
        return pacotes.get(posicao);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;

        if (convertView == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_pacote, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        Pacote pacote = pacotes.get(posicao);

        mostraLocal(holder, pacote);
        mostraImagem(holder, pacote);
        mostraDias(holder, pacote);
        mostraPreco(holder, pacote);

        return view;
    }

    private void mostraPreco(ViewHolder holder, Pacote pacote) {
        String moedaBrasileira = formataParaBrasileiro(pacote.getPreco());
        holder.preco.setText(moedaBrasileira);
    }

    private void mostraDias(ViewHolder holder, Pacote pacote) {
        String diasTexto = formataEmTexto(pacote.getDias());
        holder.dias.setText(diasTexto);
    }

    private void mostraImagem(ViewHolder holder, Pacote pacote) {
        Drawable drawableImagemPacote = devolveDrawable(context, pacote.getImagem());
        holder.imagem.setImageDrawable(drawableImagemPacote);
    }

    private void mostraLocal(ViewHolder holder, Pacote pacote) {
        holder.local.setText(pacote.getLocal());
    }

    private class ViewHolder {
        TextView preco;
        TextView dias;
        ImageView imagem;
        TextView local;

        ViewHolder(View view) {
            preco = view.findViewById(R.id.item_pacote_preco);
            dias = view.findViewById(R.id.item_pacote_dias);
            imagem = view.findViewById(R.id.item_pacote_imagem);
            local = view.findViewById(R.id.item_pacote_local);
        }
    }
}
