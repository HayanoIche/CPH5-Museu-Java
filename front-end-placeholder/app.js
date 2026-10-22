
const API_URL = 'http://localhost:8081/api/v1/obra';

carregarTodasObras();


function mostrarSecao(secao) {
    document.getElementById('secao-geral').style.display = 'none';
    document.getElementById('secao-ranking').style.display = 'none';
    document.getElementById('secao-cadastro').style.display = 'none';

    document.getElementById('secao-' + secao).style.display = 'block';

    if (secao === "ranking") {
        carregarRanking();
    }
}



async function carregarTodasObras() {
    try {
        const res = await fetch(`${API_URL}`);
        const dados = await res.json();

        const tbody = document.getElementById('tabela-geral');
        tbody.innerHTML = '';

        let posicao = 1;
        dados.forEach(o => {
            tbody.innerHTML += `
                <tr>
                    <td>${posicao}</td>
                    <td>${o.nome}</td>
                    <td>${o.artista}</td>
                    <td>${o.tipo}</td> <!-- Adicionado aqui -->
                    <td>${o.numeroDeVotos}</td>
                    <td><button onclick="votar(${o.id}, '${o.tipo.toLowerCase()}')">Votar</button></td>
                </tr>
            `;
            posicao += 1;
        });

        console.log("Obras gerais carregadas com tipo!");
    } catch (err) {
        alert('Erro ao carregar obras!');
    }
}



async function carregarRanking() {
    const tipo = document.getElementById('filtro-tipo').value;

    console.log('Buscando ranking para:', tipo);

    try {
        const res = await fetch(`${API_URL}/${tipo}`);
        const dados = await res.json();

        const tbody = document.getElementById('tabela-ranking');
        tbody.innerHTML = '';

        dados.forEach(o => {
            tbody.innerHTML += `
                <tr>
                    <td>${o.nome}</td>
                    <td>${o.artista}</td>
                    <td>${o.numeroDeVotos}</td>
                    <td><button onclick="votar(${o.id}, '${tipo}')">Votar</button></td>
                </tr>
            `;
        });

        console.log(`${tipo}s carregadas!`);
    } catch (err) {
        alert(`Erro ao carregar ranking de ${tipo}!`);
    }
}



function mudarCamposEspecificos() {
    const tipo = document.getElementById('cad-tipo').value;

    document.getElementById('campos-escultura').style.display = tipo === 'escultura' ? 'block' : 'none';
    document.getElementById('campos-fotografia').style.display = tipo === 'fotografia' ? 'block' : 'none';
    document.getElementById('campos-pintura').style.display = tipo === 'pintura' ? 'block' : 'none';
}



async function cadastrarObra(e)
{
    e.preventDefault();

    const tipo = document.getElementById('cad-tipo').value;

    // Campos comuns
    let payload = {
        nome: document.getElementById('cad-nome').value,
        artista: document.getElementById('cad-artista').value,
        descricao: document.getElementById('cad-descricao').value,
        dataCriacao: document.getElementById('cad-data').value
    };

    // Campos específicos de cada tipo
    if (tipo === 'escultura') {
        payload.material = document.getElementById('cad-material').value;
        payload.pesoKG = parseFloat(document.getElementById('cad-peso').value) || 0;
    } else if (tipo === 'fotografia') {
        payload.camera = document.getElementById('cad-camera').value;
        payload.resolucaoDPI = parseFloat(document.getElementById('cad-resolucao').value) || 0;
        payload.colorida = document.getElementById('cad-colorida').checked;
    } else if (tipo === 'pintura') {
        // payload da pintura aqui quando implementar
    }

    try {
        const resposta = await fetch(`${API_URL}/${tipo}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        if (resposta.ok) {
            alert(`${tipo.toUpperCase()} cadastrada com sucesso!`);
            document.getElementById('form-cadastro').reset();
            mudarCamposEspecificos();
        } else {
            alert(`Erro ao cadastrar ${tipo}.`);
        }
    } catch (erro) {
        console.error('Erro de conexão:', erro);
    }
}



async function votar(id, tipo) {
    try {
        const resposta = await fetch(`${API_URL}/${tipo}/${id}/votar`, {
            method: 'PUT'
        });

        if (resposta.ok) {
            alert('Voto computado com sucesso!');
            // Recarrega a tela em que o usuário está
            if (document.getElementById('secao-geral').style.display !== 'none') {
                carregarTodasObras();
            } else {
                carregarRanking();
            }
        } else {
            alert('Erro ao registrar voto.');
        }
    } catch (erro) {
        console.error('Erro de conexão:', erro);
    }
}


