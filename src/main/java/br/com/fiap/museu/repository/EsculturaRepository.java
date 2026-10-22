package br.com.fiap.museu.repository;

import br.com.fiap.museu.model.Escultura;
import org.jspecify.annotations.NonNull;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EsculturaRepository
{
    private DataSource dataSource;

    public EsculturaRepository(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public List<Escultura> getAll() {
        String sql = "SELECT id, nome, artista, descricao, numero_de_votos, data_criacao, material, peso_kg FROM tb_esculturas_jv";
        Connection con = DataSourceUtils.getConnection(dataSource);
        List<Escultura> esculturas = new ArrayList<>();

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Escultura e = new Escultura();
                e.setId(rs.getInt("id"));
                e.setNome(rs.getString("nome"));
                e.setArtista(rs.getString("artista"));
                e.setDescricao(rs.getString("descricao"));
                e.setNumeroDeVotos(rs.getInt("numero_de_votos"));
                if (rs.getDate("data_criacao") != null) {
                    e.setDataCriacao(rs.getDate("data_criacao").toLocalDate());
                }
                e.setMaterial(rs.getString("material"));
                e.setPesoKG(rs.getDouble("peso_kg"));
                esculturas.add(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar esculturas", e);
        } finally {
            DataSourceUtils.releaseConnection(con, dataSource);
        }
        return esculturas;
    }
}
