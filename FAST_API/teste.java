// Arquivo: OpenLibraryClient.java

// TESTE DE CONSUMO DE API EM JAVA FEITO POR IA

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Exemplo de consumo da API OpenLibrary em Java
 * Dependência usada: Gson (para parsear JSON)
 *
 * Adicione no seu pom.xml (Maven):
 *
 * <dependency>
 *   <groupId>com.google.code.gson</groupId>
 *   <artifactId>gson</artifactId>
 *   <version>2.10.1</version>
 * </dependency>
 */
public class OpenLibraryClient {

    private static final String API_URL = "https://openlibrary.org/search.json?title=";

    public static void main(String[] args) {
        try {
            String titulo = "harry potter"; // título de exemplo
            String url = API_URL + titulo.replace(" ", "+");

            // Faz a requisição HTTP GET
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                // Lê a resposta JSON
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Converte para objeto JSON usando Gson
                JsonObject json = JsonParser.parseString(response.toString()).getAsJsonObject();

                // Pega a lista de docs (livros encontrados)
                JsonArray docs = json.getAsJsonArray("docs");

                System.out.println("📚 Resultados encontrados para: " + titulo);
                System.out.println("--------------------------------------");

                // Mostra os 5 primeiros livros encontrados
                for (int i = 0; i < Math.min(5, docs.size()); i++) {
                    JsonObject livro = docs.get(i).getAsJsonObject();

                    String tituloLivro = livro.has("title") ? livro.get("title").getAsString() : "Título desconhecido";
                    String autor = livro.has("author_name") ? livro.get("author_name").getAsJsonArray().get(0).getAsString() : "Autor desconhecido";
                    String ano = livro.has("first_publish_year") ? String.valueOf(livro.get("first_publish_year").getAsInt()) : "Ano desconhecido";

                    System.out.println("Título: " + tituloLivro);
                    System.out.println("Autor: " + autor);
                    System.out.println("Ano: " + ano);
                    System.out.println("--------------------------------------");
                }

            } else {
                System.out.println("Erro na requisição. Código: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}