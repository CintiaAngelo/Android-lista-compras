package CintiaAngelo.com.github.android_lista_de_compras

import CintiaAngelo.com.github.android_lista_de_compras.ItensViewModel.ItemsViewModel
import CintiaAngelo.com.github.android_lista_de_compras.R.id
import CintiaAngelo.com.github.android_lista_de_compras.R.layout
import CintiaAngelo.com.github.android_lista_de_compras.viewmodel.ItemsAdapter
import CintiaAngelo.com.github.android_lista_de_compras.viewmodel.ItemsViewModelFactory
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView


/**
 * A activity principal da aplicação.
 * Esta activity é responsável por exibir a lista de itens e fornecer uma interface para adicionar novos itens à lista.
 * A activity usa um `ItemsViewModel` para interagir com o banco de dados.
 *
 * @property viewModel O ViewModel usado para interagir com o banco de dados.
 * @author Ewerton Carreira
 * @version 1.0
 * @since 2023-02-01
 */
class MainActivity : AppCompatActivity() {

    // O ViewModel usado para interagir com o banco de dados.
    private lateinit var viewModel: ItemsViewModel

    /**
     * Chamado quando a activity é criada.
     * Este método configura a interface do usuário e inicializa o ViewModel.
     *
     * @param savedInstanceState Se a activity está sendo recriada a partir de um estado salvo, este é o estado.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        // Chama o método onCreate da superclasse para completar a criação da activity.
        super.onCreate(savedInstanceState)
        // Define o layout da activity.
        setContentView(layout.activity_main)

        // Encontra a barra de ferramentas pelo seu ID e a define como a barra de ação para esta activity.
        val toolbar: Toolbar = findViewById(id.toolbar)
        setSupportActionBar(toolbar)
        // Define o título da barra de ação.
        supportActionBar?.title = "Lista de Compras"

        // Encontra o RecyclerView pelo seu ID.
        val recyclerView = findViewById<RecyclerView>(id.recyclerView)
        // Cria um novo adaptador para o RecyclerView. O adaptador é responsável por exibir os itens na lista.
        // Quando um item é clicado, ele é removido da lista.
        val itemsAdapter = ItemsAdapter { item ->
            viewModel.removeItem(item)
        }
        // Define o adaptador do RecyclerView.
        recyclerView.adapter = itemsAdapter

        // Encontra o botão e o campo de texto pelo seus IDs.
        val button = findViewById<Button>(id.button)
        val editText = findViewById<EditText>(id.editText)

        // Define o que acontece quando o botão é clicado.
        button.setOnClickListener {
            // Se o campo de texto estiver vazio, exibe um erro e retorna.
            if (editText.text.isEmpty()) {
                editText.error = "Preencha um valor"
                return@setOnClickListener
            }

            // Adiciona o item ao ViewModel e limpa o campo de texto.
            viewModel.addItem(editText.text.toString())
            editText.text.clear()
        }

        // Cria uma nova fábrica para o ViewModel.
        val viewModelFactory = ItemsViewModelFactory(application)
        // Obtém uma instância do ViewModel.
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemsViewModel::class.java)

        // Observa as mudanças na lista de itens e atualiza o adaptador quando a lista muda.
        viewModel.itemsLiveData.observe(this) { items ->
            itemsAdapter.updateItems(items)
        }
    }
}