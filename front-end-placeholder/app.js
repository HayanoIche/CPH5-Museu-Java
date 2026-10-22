
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
                    <td>${o.id}</td>
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
                        <td>${o.id}</td>
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



async function cadastrarObra(e) {
    e.preventDefault();
    const tipo = document.getElementById('cad-tipo').value;

    const payload = {
        nome: document.getElementById('cad-nome').value,
        artista: document.getElementById('cad-artista').value,
        descricao: document.getElementById('cad-descricao').value,
        dataCriacao: document.getElementById('cad-data').value,
        material: document.getElementById('cad-material').value,
        pesoKG: parseFloat(document.getElementById('cad-peso').value)
    };

    // TODO: Enviar POST para o endpoint correto
    console.log('Enviando cadastro de:', tipo, payload);
}



async function votar(id) {
    // TODO: enviar as informações novas pra api
    console.log('Voto registrado para ID:', id);
}


