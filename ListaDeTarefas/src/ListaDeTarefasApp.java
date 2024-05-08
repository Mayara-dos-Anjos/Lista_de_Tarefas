import javax.swing.*;

public class ListaDeTarefasApp {
    private static DefaultListModel<String> modeloLista;
    private static JList<String> listaTarefas;
    private static JTextField campoTexto;

    public static void main(String[] args){
    SwingUtilities.invokeLater(ListaDeTarefasApp::criarEExibirGUI);
    }
    private static void criarEExibirGUI() {
        JFrame frame = new JFrame("Lista de Tarefas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);

        JPanel painel = new JPanel();
        frame.add(painel);

        GroupLayout layout = new GroupLayout(painel);
        painel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel label = new JLabel("Nova Tarefa:");
        campoTexto = new JTextField(20);
        JButton botaoAdicionar = new JButton("Adicionar");
        JButton botaoConcluir = new JButton("Concluir");
        JButton botaoRemover = new JButton("Remover");
        modeloLista = new DefaultListModel<>();
        listaTarefas = new JList<>(modeloLista);
        JScrollPane scrollpane = new JScrollPane(listaTarefas);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label)
                        .addComponent(campoTexto)
                        .addComponent(botaoAdicionar)
                        .addComponent(botaoConcluir)
                        .addComponent(botaoRemover))
                .addComponent(scrollpane)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(campoTexto))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoAdicionar))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoConcluir))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoRemover))
                .addComponent(scrollpane)
        );
        botaoAdicionar.addActionListener(e -> adicionarTarefa());
        botaoConcluir.addActionListener(e -> marcarComoConcluido());
        botaoRemover.addActionListener(e -> apagarTarefa());

        frame.setVisible(true);
    }
    private static void adicionarTarefa() {
        String novaTarefa = campoTexto.getText();
        if (!novaTarefa.isEmpty()) {
            modeloLista.addElement(novaTarefa);
            campoTexto.setText("");
        }
    }

    private static void marcarComoConcluido() {
        int indexSelecionado = listaTarefas.getSelectedIndex();
        if (indexSelecionado != -1) {
            modeloLista.setElementAt("[X] " + modeloLista.getElementAt(indexSelecionado), indexSelecionado);
        }
    }

    private static void apagarTarefa() {
        int indexSelecionado = listaTarefas.getSelectedIndex();
        if (indexSelecionado != -1) {
            modeloLista.remove(indexSelecionado);
        }
    }
}
