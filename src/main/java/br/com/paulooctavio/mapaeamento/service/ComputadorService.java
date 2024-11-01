package br.com.paulooctavio.mapaeamento.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.paulooctavio.mapaeamento.model.Computador;
import br.com.paulooctavio.mapaeamento.repository.ComputadorRepository;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class ComputadorService {

    @Value("${milvus.api.url}")
    private String baseUrl;
    @Value("${milvus.api.authorization}")
    private String authorization;

    @Autowired
    private ComputadorRepository computadorRepository;

    public Computador getComputadorDataMilvus(Computador computador) {

        if (computador.getIdMilvus() == null || computador.getIdMilvus() == 0) {
            return computador;
        }

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(baseUrl + "dispositivos/buscar?id=" + computador.getIdMilvus())
                .get()
                .addHeader("Authorization", authorization)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                // decode json
                ObjectMapper mapper = new ObjectMapper();

                String json = response.body().string();

                computador.setNome(mapper.readTree(json).get("lista").get(0).get("hostname").asText());
                computador.setProcessador(mapper.readTree(json).get("lista").get(0).get("processador").asText());
                computador.setMemoria(mapper.readTree(json).get("lista").get(0).get("ram_total").asText());
                if (mapper.readTree(json).get("lista").get(0).get("armazenamento") != null &&
                        !mapper.readTree(json).get("lista").get(0).get("armazenamento").asText().equals("null")) {
                    computador
                            .setArmazenamento(mapper.readTree(json).get("lista").get(0).get("armazenamento").asText());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return computador;

    }

    public List<Computador> findByFuncionarioId(Integer id) {
        List<Computador> computadores = this.computadorRepository.findByFuncionarioId(id);

        computadores = computadores.stream().map(
                c -> {
                    return this.getComputadorDataMilvus(c);
                }).collect(Collectors.toList());

        return computadores;
    }

    public List<Computador> findAll() {
        List<Computador> computadores = this.computadorRepository.findAll();

        computadores.stream().forEach(
                c -> {
                    c = this.getComputadorDataMilvus(c);
                });

        return computadores;
    }
}
