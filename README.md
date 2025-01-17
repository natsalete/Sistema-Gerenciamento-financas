﻿# Sistema-Gerenciamento-financas
# Material de Estudos: Padrões de Projeto (Design Patterns)

## 1. Padrão Adapter

### Conceito
O Adapter é um padrão estrutural que permite que interfaces incompatíveis trabalhem juntas. Ele atua como um wrapper entre dois objetos, convertendo a interface de uma classe em outra interface que o cliente espera.

### Quando Usar
- Quando você precisa usar uma classe existente, mas sua interface não é compatível
- Para reutilizar classes existentes que não possuem a interface que você necessita
- Para criar uma classe reutilizável que coopere com classes que não possuem interfaces compatíveis

### Exemplo Prático
```java
// Interface esperada pelo cliente
interface MediaPlayer {
    void play(String filename);
}

// Interface incompatível
interface AdvancedMediaPlayer {
    void playMp4(String filename);
    void playVlc(String filename);
}

// Implementação do Advanced Player
class VlcPlayer implements AdvancedMediaPlayer {
    public void playVlc(String filename) {
        System.out.println("Tocando arquivo VLC: " + filename);
    }
    public void playMp4(String filename) {
        // não faz nada
    }
}

// Adapter
class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType) {
        if(audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer = new VlcPlayer();
        }
    }

    public void play(String filename) {
        advancedMusicPlayer.playVlc(filename);
    }
}
```

## 2. Padrão Composite

### Conceito
O Composite é um padrão estrutural que permite compor objetos em estruturas de árvore e trabalhar com essas estruturas como se fossem objetos individuais.

### Quando Usar
- Quando você precisa implementar uma estrutura de objetos do tipo árvore
- Quando os clientes precisam tratar tanto objetos individuais quanto composições de objetos de maneira uniforme

### Exemplo Prático
```java
// Componente base
abstract class ArquivoSistema {
    protected String nome;
    
    public ArquivoSistema(String nome) {
        this.nome = nome;
    }
    
    abstract void mostrar();
}

// Folha
class Arquivo extends ArquivoSistema {
    public Arquivo(String nome) {
        super(nome);
    }
    
    public void mostrar() {
        System.out.println("Arquivo: " + nome);
    }
}

// Composite
class Diretorio extends ArquivoSistema {
    private List<ArquivoSistema> arquivos = new ArrayList<>();
    
    public Diretorio(String nome) {
        super(nome);
    }
    
    public void adicionar(ArquivoSistema arquivo) {
        arquivos.add(arquivo);
    }
    
    public void mostrar() {
        System.out.println("Diretório: " + nome);
        for(ArquivoSistema arquivo : arquivos) {
            arquivo.mostrar();
        }
    }
}
```

## 3. Padrão Factory

### Conceito
O Factory é um padrão criacional que fornece uma interface para criar objetos em uma superclasse, mas permite que as subclasses alterem o tipo de objetos que serão criados.

### Quando Usar
- Quando uma classe não pode antecipar a classe de objetos que deve criar
- Quando você quer delegar a responsabilidade de criação de objetos para subclasses
- Quando você quer encapsular a criação de objetos

### Exemplo Prático
```java
// Produto
interface Animal {
    void fazerSom();
}

// Produtos Concretos
class Cachorro implements Animal {
    public void fazerSom() {
        System.out.println("Au Au!");
    }
}

class Gato implements Animal {
    public void fazerSom() {
        System.out.println("Miau!");
    }
}

// Factory
class AnimalFactory {
    public Animal criarAnimal(String tipo) {
        if (tipo.equalsIgnoreCase("cachorro")) {
            return new Cachorro();
        } else if (tipo.equalsIgnoreCase("gato")) {
            return new Gato();
        }
        return null;
    }
}
```

## 4. Padrão Observer

### Conceito
O Observer é um padrão comportamental que define uma dependência um-para-muitos entre objetos, de modo que quando um objeto muda de estado, todos os seus dependentes são notificados e atualizados automaticamente.

### Quando Usar
- Quando uma mudança em um objeto requer mudanças em outros
- Quando um objeto precisa notificar outros objetos sem fazer suposições sobre quem são esses objetos
- Quando você precisa manter consistência entre objetos relacionados

### Exemplo Prático
```java
// Subject Interface
interface Subject {
    void registrarObserver(Observer o);
    void removerObserver(Observer o);
    void notificarObservers();
}

// Observer Interface
interface Observer {
    void update(String mensagem);
}

// Concrete Subject
class Canal implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String ultimoVideo;
    
    public void registrarObserver(Observer o) {
        observers.add(o);
    }
    
    public void removerObserver(Observer o) {
        observers.remove(o);
    }
    
    public void notificarObservers() {
        for (Observer observer : observers) {
            observer.update(ultimoVideo);
        }
    }
    
    public void publicarVideo(String video) {
        this.ultimoVideo = video;
        notificarObservers();
    }
}

// Concrete Observer
class Inscrito implements Observer {
    private String nome;
    
    public Inscrito(String nome) {
        this.nome = nome;
    }
    
    public void update(String video) {
        System.out.println(nome + " recebeu notificação do vídeo: " + video);
    }
}
```

## 5. Padrão Singleton

### Conceito
O Singleton é um padrão criacional que garante que uma classe tenha apenas uma instância e fornece um ponto global de acesso a ela.

### Quando Usar
- Quando você precisa de exatamente uma instância de uma classe
- Quando você precisa de controle estrito sobre variáveis globais
- Quando você precisa compartilhar estado entre diferentes partes da aplicação

### Exemplo Prático
```java
public class ConfiguracaoSistema {
    private static ConfiguracaoSistema instancia;
    private Map<String, String> configuracoes;
    
    private ConfiguracaoSistema() {
        configuracoes = new HashMap<>();
    }
    
    public static ConfiguracaoSistema getInstancia() {
        if (instancia == null) {
            synchronized (ConfiguracaoSistema.class) {
                if (instancia == null) {
                    instancia = new ConfiguracaoSistema();
                }
            }
        }
        return instancia;
    }
    
    public void setConfiguracao(String chave, String valor) {
        configuracoes.put(chave, valor);
    }
    
    public String getConfiguracao(String chave) {
        return configuracoes.get(chave);
    }
}
```

## 6. Padrão Template

### Conceito
O Template é um padrão comportamental que define o esqueleto de um algoritmo em um método, deixando alguns passos para serem implementados pelas subclasses. O padrão permite que as subclasses redefinam certos passos de um algoritmo sem mudar sua estrutura.

### Quando Usar
- Quando você tem vários algoritmos que possuem estruturas similares
- Quando você quer evitar duplicação de código
- Quando você quer permitir que as subclasses alterem certos passos do algoritmo

### Exemplo Prático
```java
abstract class Bebida {
    // Método template
    final void prepararBebida() {
        ferverAgua();
        adicionarIngredientes();
        if (clienteQuerCondimentos()) {
            adicionarCondimentos();
        }
        colocarNaXicara();
    }
    
    abstract void adicionarIngredientes();
    abstract void adicionarCondimentos();
    
    void ferverAgua() {
        System.out.println("Fervendo água");
    }
    
    void colocarNaXicara() {
        System.out.println("Colocando na xícara");
    }
    
    // Hook method
    boolean clienteQuerCondimentos() {
        return true;
    }
}

class Cafe extends Bebida {
    void adicionarIngredientes() {
        System.out.println("Passando o café");
    }
    
    void adicionarCondimentos() {
        System.out.println("Adicionando açúcar e leite");
    }
}
```

## 7. Padrão MVC (Model-View-Controller)

### Conceito
O MVC é um padrão arquitetural que divide uma aplicação em três componentes principais:
- Model: Gerencia os dados e a lógica de negócio
- View: Apresenta os dados ao usuário
- Controller: Gerencia a interação do usuário e atualiza Model e View

### Quando Usar
- Em aplicações web
- Quando você precisa de uma clara separação entre dados, apresentação e lógica
- Quando você quer permitir a modificação independente de cada componente

### Exemplo Prático
```java
// Model
class Usuario {
    private String nome;
    private String email;
    
    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
    
    // getters e setters
}

// View
class UsuarioView {
    public void mostrarDetalhes(String nome, String email) {
        System.out.println("Usuário:");
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
    }
}

// Controller
class UsuarioController {
    private Usuario model;
    private UsuarioView view;
    
    public UsuarioController(Usuario model, UsuarioView view) {
        this.model = model;
        this.view = view;
    }
    
    public void atualizarView() {
        view.mostrarDetalhes(model.getNome(), model.getEmail());
    }
    
    public void setUsuarioNome(String nome) {
        model.setNome(nome);
    }
    
    public void setUsuarioEmail(String email) {
        model.setEmail(email);
    }
}
```

## Dicas para a Prova

1. **Diferencie os tipos de padrões:**
   - Criacionais (Factory, Singleton)
   - Estruturais (Adapter, Composite)
   - Comportamentais (Observer, Template)
   - Arquiteturais (MVC)

2. **Pontos-chave para cada padrão:**
   - Adapter: Compatibilidade entre interfaces
   - Composite: Estrutura em árvore
   - Factory: Criação flexível de objetos
   - Observer: Notificação de mudanças
   - Singleton: Instância única
   - Template: Esqueleto de algoritmo
   - MVC: Separação de responsabilidades

3. **Identifique os cenários de uso:**
   - Analise o problema apresentado
   - Identifique as características que indicam qual padrão usar
   - Considere as vantagens e desvantagens de cada padrão

4. **Práticas de implementação:**
   - Mantenha o código limpo e organizado
   - Use nomes descritivos para classes e métodos
   - Siga os princípios SOLID
   - Documente decisões importantes
