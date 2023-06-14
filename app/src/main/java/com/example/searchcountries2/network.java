package com.example.searchcountries2;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class network {
    private static final String LOG_TAG = network.class.getSimpleName();
    // Constantes utilizadas pela API
    // URL para a API de Livros do Google.
    private static final String NAME_URL = "https://restcountries.com/v3.1/name/" + "";
    // Parametros da string de Busca
    // Limitador da qtde de resultados
    private static final String MAX_RESULTS = "maxResults";
    // Parametro do tipo de impressão
    private static final String TIPO_IMPRESSAO = "printType";

    static String buscaPais(String queryString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String name= null;
        try {
            // Construção da URI de Busca
            Uri builtURI = Uri.parse(NAME_URL).buildUpon()
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(TIPO_IMPRESSAO, "books")
                    .build();
            // Converte a URI para a URL.
            URL requestURL = new URL(builtURI.toString());
            // Abre a conexão de rede
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Busca o InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            // Cria o buffer para o input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));
            // Usa o StringBuilder para receber a resposta.
            StringBuilder builder = new StringBuilder();
            if (builder.length() == 0) {
                // se o stream estiver vazio não faz nada
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // fecha a conexão e o buffer.
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // escreve o Json no log
        Log.d(LOG_TAG, name);
        return name;
    }
}


