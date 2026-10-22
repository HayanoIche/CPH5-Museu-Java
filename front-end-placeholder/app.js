
const API_URL = 'http://localhost:8081/api/v1/obra';

carregarTodasObras();



function mostrarSecao(secao)
{
    document.getElementById('secao-geral').style.display = 'none';
    document.getElementById('secao-ranking').style.display = 'none';
    document.getElementById('secao-cadastro').style.display = 'none';

    document.getElementById('secao-' + secao).style.display = 'block';

    if (secao === "ranking")
    {
        carregarRanking();
    }
}



async function carregarTodasObras()
{
    try {
        const res = await fetch(`${API_URL}/escultura`);
        const dados = await res.json();

        const tbody = document.getElementById('tabela-geral');
        tbody.innerHTML = '';

        dados.forEach(o => {
            tbody.innerHTML += `
                <tr>
                    <td>${o.nome}</td>
                    <td>${o.artista}</td>
                    <td>${o.numeroDeVotos}</td>
                    <td><button onclick="votar(${o.id})">Votar</button></td>
                </tr>
            `;
        });

        console.log("obras carregadas!")
    } catch (err) {
        alert('Erro ao carregar obras!');
    }
}



async function carregarRanking()
{
    const tipo = document.getElementById('filtro-tipo').value;

    console.log('Buscando ranking para:', tipo);

    if (tipo === "escultura")
    {
        try {
            const res = await fetch(`${API_URL}/escultura`);
            const dados = await res.json();

            const tbody = document.getElementById('tabela-ranking');
            tbody.innerHTML = '';

            dados.forEach(o => {
                tbody.innerHTML += `
                    <tr>
                        <td>${o.nome}</td>
                        <td>${o.artista}</td>
                        <td>${o.numeroDeVotos}</td>
                        <td><button onclick="votar(${o.id})">Votar</button></td>
                    </tr>
                `;
            });

            console.log("esculturas carregadas!")
        } catch (err) {
            alert('Erro ao carregar obras!');
        }
    }

    if (tipo === "fotografia")
    {
        // puxar as fotografias
    }

    if (tipo === "escultura")
    {
        // puxar as esculturas
    }
}



// Funcao pra basicamente mudar os campos a mais que cada um dos tipos de obra tem
function mudarCamposEspecificos()
{
    const tipo = document.getElementById('cad-tipo').value;

    document.getElementById('campos-escultura').style.display = tipo === 'escultura' ? 'block' : 'none';
    document.getElementById('campos-fotografia').style.display = tipo === 'fotografia' ? 'block' : 'none';
    document.getElementById('campos-pintura').style.display = tipo === 'pintura' ? 'block' : 'none';
}



async function cadastrarObra(e)
{
    {
        e.preventDefault();

        const payload = {
            nome: document.getElementById('cad-nome').value,
            artista: document.getElementById('cad-artista').value,
            descricao: document.getElementById('cad-descricao').value,
            dataCriacao: document.getElementById('cad-data').value,
            material: document.getElementById('cad-material').value,
            pesoKG: parseFloat(document.getElementById('cad-peso').value)
        };

        try {
            const resposta = await fetch(`${API_URL}/escultura`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });

            if (resposta.ok) {
                alert('Escultura cadastrada com sucesso!');
                document.getElementById('form-cadastro').reset();
            } else {
                alert('Erro ao cadastrar escultura.');
            }
        } catch (erro) {
            console.error('Erro de conexão:', erro);
        }
    }
}



async function votar(id) {
    try {
        const resposta = await fetch(`${API_URL}/escultura/${id}/votar`, {
            method: 'PUT'
        });

        if (resposta.ok) {
            alert('Voto computado com sucesso!');
            carregarTodasObras(); // Recarrega a tabela para atualizar os votos na tela
        } else {
            alert('Erro ao registrar voto.');
        }
    } catch (erro) {
        console.error('Erro de conexão:', erro);
    }
}


