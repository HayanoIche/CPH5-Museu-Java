package br.com.fiap.museu.repository;

import br.com.fiap.museu.model.Escultura;
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

    public void save(Escultura escultura)
    {
        String sql = "INSERT INTO tb_esculturas_jv (nome, artista, descricao, numero_de_votos, data_criacao, material, peso_kg) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection con = DataSourceUtils.getConnection(dataSource);
        try (PreparedStatement pstmt = con.prepareStatement(sql, new String[]{"id"})) {
            pstmt.setString(1, escultura.getNome());
            pstmt.setString(2, escultura.getArtista());
            pstmt.setString(3, escultura.getDescricao());
            pstmt.setInt(4, escultura.getNumeroDeVotos());
            pstmt.setDate(5, escultura.getDataCriacao() != null ? Date.valueOf(escultura.getDataCriacao()) : null);
            pstmt.setString(6, escultura.getMaterial());
            pstmt.setDouble(7, escultura.getPesoKG());

            int registros = pstmt.executeUpdate();
            if (registros != 1) throw new RuntimeException("Nenhuma escultura adicionada");

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                escultura.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro na inserção da escultura", e);
        } finally {
            DataSourceUtils.releaseConnection(con, dataSource);
        }
    }

    public List<Escultura> getAll()
    {
        String sql = "SELECT id, nome, artista, descricao, numero_de_votos, data_criacao, material, peso_kg FROM tb_esculturas_jv ORDER BY numero_de_votos DESC";
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

    public void updateVotos(int id)
    {
        String sql = "UPDATE tb_esculturas_jv SET numero_de_votos = numero_de_votos + 1 WHERE id = ?";
        Connection con = DataSourceUtils.getConnection(dataSource);
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int registros = pstmt.executeUpdate();
            if (registros != 1) {
                throw new RuntimeException("Escultura não encontrada para votação");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar voto", e);
        } finally {
            DataSourceUtils.releaseConnection(con, dataSource);
        }
    }

}
