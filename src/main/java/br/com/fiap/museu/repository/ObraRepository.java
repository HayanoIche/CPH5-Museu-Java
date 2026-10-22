package br.com.fiap.museu.repository;

import br.com.fiap.museu.model.Escultura;
import br.com.fiap.museu.model.Fotografia;
import br.com.fiap.museu.model.Obra;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ObraRepository
{
    private final DataSource dataSource;

    public ObraRepository(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public List<Obra> getAllObras()
    {
        String sql = """
            SELECT id, nome, artista, descricao, numero_de_votos, data_criacao, 'ESCULTURA' AS tipo FROM tb_esculturas_jv
            UNION ALL
            SELECT id, nome, artista, descricao, numero_de_votos, data_criacao, 'FOTOGRAFIA' AS tipo FROM tb_fotos_jv
            ORDER BY numero_de_votos DESC
        """;

        Connection con = DataSourceUtils.getConnection(dataSource);
        List<Obra> obras = new ArrayList<>();

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String tipo = rs.getString("tipo");

                if ("ESCULTURA".equals(tipo)) {
                    Escultura e = new Escultura();
                    e.setId(rs.getInt("id"));
                    e.setNome(rs.getString("nome"));
                    e.setArtista(rs.getString("artista"));
                    e.setDescricao(rs.getString("descricao"));
                    e.setNumeroDeVotos(rs.getInt("numero_de_votos"));
                    if (rs.getDate("data_criacao") != null) {
                        e.setDataCriacao(rs.getDate("data_criacao").toLocalDate());
                    }
                    obras.add(e);
                } else if ("FOTOGRAFIA".equals(tipo)) {
                    Fotografia f = new Fotografia();
                    f.setId(rs.getInt("id"));
                    f.setNome(rs.getString("nome"));
                    f.setArtista(rs.getString("artista"));
                    f.setDescricao(rs.getString("descricao"));
                    f.setNumeroDeVotos(rs.getInt("numero_de_votos"));
                    if (rs.getDate("data_criacao") != null) {
                        f.setDataCriacao(rs.getDate("data_criacao").toLocalDate());
                    }
                    obras.add(f);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar todas as obras", e);
        } finally {
            DataSourceUtils.releaseConnection(con, dataSource);
        }
        return obras;
    }
}