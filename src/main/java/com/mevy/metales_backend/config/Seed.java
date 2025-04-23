package com.mevy.metales_backend.config;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mevy.metales_backend.entities.Category;
import com.mevy.metales_backend.entities.Chapter;
import com.mevy.metales_backend.entities.Tale;
import com.mevy.metales_backend.entities.User;
import com.mevy.metales_backend.entities.enums.TaleStatus;
import com.mevy.metales_backend.repositories.CategoryRepository;
import com.mevy.metales_backend.repositories.ChapterRepository;
import com.mevy.metales_backend.repositories.TaleRepository;
import com.mevy.metales_backend.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class Seed implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TaleRepository taleRepository;
    private final CategoryRepository categoryRepository;
    private final ChapterRepository chapterRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        User user1 = User.builder()
                .username("eldor")
                .email("eldor@example.com")
                .password(passwordEncoder.encode("123456"))
                .build();

        User user2 = User.builder()
                .username("cryon")
                .email("cryon@example.com")
                .password(passwordEncoder.encode("654321"))
                .build();

        User user3 = User.builder()
                .username("nova")
                .email("nova@example.com")
                .password(passwordEncoder.encode("abcdef"))
                .build();

        userRepository.saveAll(
            Set.of(user1, user2, user3)
        );

        Category catAction = Category.builder().name("Ação").build();
        Category catFantasy = Category.builder().name("Fantasia").build();
        Category catAdventure = Category.builder().name("Aventura").build();
        Category catDrama = Category.builder().name("Drama").build();
        Category catScifi = Category.builder().name("Sci-fi").build();
        Category catMystery = Category.builder().name("Mistério").build();
        Category catRomance = Category.builder().name("Romance").build();

        categoryRepository.saveAll(
            Set.of(
                catAction, catFantasy, catAdventure,
                catDrama, catScifi, catMystery, catRomance
            )
        );

        Tale tale1 = Tale.builder()
                .name("Ecos de Eldoria")
                .description("""
                    Em um continente esquecido pelo tempo, onde as ruínas cantam canções de eras antigas, 
                    um grupo de heróis desperta de séculos de silêncio. Fragmentos de uma magia primordial 
                    sussurram entre as árvores e montanhas de Eldoria, chamando aqueles que ousam sonhar com a redenção.

                    À medida que sombras se erguem e o passado cobra seu preço, esses heróis precisam unir forças 
                    para restaurar um mundo dilacerado por traições, guerras e esperanças perdidas.
                """)
                .creationDate(Instant.now())
                .UpdationDate(Instant.now())
                .status(TaleStatus.ONGOING.getCode())
                .author(user1)
                .categories(Set.of(catFantasy, catAdventure, catAction))
                .build();

        Tale tale2 = Tale.builder()
                .name("Gelo Eterno")
                .description("""
                    Em terras onde o sol raramente nasce, clãs moldados pela neve travam batalhas contra criaturas 
                    forjadas no âmago da era glacial. Entre florestas congeladas e montanhas implacáveis, 
                    antigos segredos vêm à tona, ameaçando quebrar o frágil equilíbrio entre sobrevivência e destruição.
        
                    Uma guerra se aproxima, não apenas entre povos, mas contra o próprio passado — que retorna 
                    com dentes de gelo e olhos cheios de rancor. Quem sobreviverá à Última Geada?
                """)
                .creationDate(Instant.now())
                .UpdationDate(Instant.now())
                .status(TaleStatus.HIATUS.getCode())
                .author(user2)
                .categories(Set.of(catFantasy, catDrama))
                .build();

        Tale tale3 = Tale.builder()
                .name("Luzes de Neon")
                .description("""
                    Em uma metrópole decadente, onde cada rua pulsa com neons e perigos digitais, 
                    memórias são a nova moeda. Hackers caçam verdades ocultas, corporações reescrevem vidas, 
                    e a realidade é tão volátil quanto os dados que a sustentam.

                    Uma mulher acorda sem passado, mas com códigos escondidos em seu DNA que podem mudar o destino da humanidade. 
                    Em um jogo de identidades fragmentadas e conspirações tecnológicas, até a verdade pode ser um programa malicioso.
                """)
                .creationDate(Instant.now())
                .UpdationDate(Instant.now())
                .status(TaleStatus.FINALIZED.getCode())
                .author(user3)
                .categories(Set.of(catScifi, catMystery))
                .build();

        Tale tale4 = Tale.builder()
                .name("Entre Estrelas e Promessas")
                .description("""
                    Em um universo onde viagens interplanetárias são comuns, os sentimentos ainda desafiam as leis da física. 
                    Separados por anos-luz, dois jovens descobrem uma conexão inexplicável que transcende barreiras tecnológicas 
                    e temporais.
        
                    Mensagens codificadas nas estrelas, sonhos compartilhados em sincronia, e uma promessa antiga 
                    que ecoa entre galáxias. O amor pode sobreviver ao vazio do espaço... mas será suficiente para unir dois mundos?
                """)
                .creationDate(Instant.now()).creationDate(Instant.now())
                .UpdationDate(Instant.now())
                .status(TaleStatus.ONGOING.getCode())
                .author(user3)
                .categories(Set.of(catRomance, catScifi))
                .build();

        taleRepository.saveAll(
            Set.of(tale1, tale2, tale3, tale4)
        );

        Chapter cap1 = Chapter.builder()
                                .name("Prólogo - Ecos Despertam")
                                .content("""
                                    Nas ruínas esquecidas de Eldoria, um sussurro ecoa entre os ventos. 
                                    As lendas dizem que a magia antiga ainda dorme sob as pedras cobertas de musgo, 
                                    aguardando aqueles corajosos o suficiente para escutar.
                                    
                                    Em uma noite sem lua, um brilho azul rompeu o céu como uma lágrima divina.
                                    Foi o primeiro sinal de que os Ecos estavam despertando...
                                    
                                    Aqueles que se esconderam nas sombras começam a se mover.
                                    Os heróis esquecidos sentem o chamado ressoar em seus corações.
                                    """)
                                .creationDate(Instant.now())
                                .build();

        Chapter cap2 = Chapter.builder()
                                .name("O Chamado")
                                .content("""
                                    A brisa do norte carregava consigo a urgência do destino.
                                    Em uma vila isolada, um jovem ouvia as histórias antigas com mais atenção que nunca.
                                    
                                    As runas na parede começaram a brilhar misteriosamente.
                                    Seu sangue pulsava em resposta, como se algo ancestral despertasse dentro dele.
                                    
                                    E então, o chamado veio — não com palavras, mas com visões de gelo, fogo e aço.
                                    """)
                                .creationDate(Instant.now())
                                .build();

        Chapter cap3 = Chapter.builder()
                                .name("Nevasca de Sangue")
                                .content("""
                                    O vento cortava como lâminas enquanto a batalha se formava no vale nevado.
                                    Guerreiros envoltos em peles encaravam criaturas cujos olhos brilhavam com ódio ancestral.
                                    
                                    O primeiro golpe foi silencioso.
                                    O segundo, ensurdecedor.
                                    
                                    Quando a neve parou de cair, ela estava tingida de vermelho.
                                    """)
                                .creationDate(Instant.now())
                                .build();

        Chapter cap4 = Chapter.builder()
                                .name("Memórias Fragmentadas")
                                .content("""
                                    Ela acordou em uma cela de luz fria.
                                    Seu nome, esquecido. Sua identidade, reconstruída com dados roubados.
                                    
                                    Fragmentos de lembranças surgiam como faíscas: um beijo, uma fuga, um código.
                                    Tudo parte de algo maior, um sistema que a caçava enquanto precisava dela para sobreviver.
                                    
                                    As memórias não mentem. Mas também não dizem toda a verdade.
                                    """)
                                .creationDate(Instant.now())
                                .build();

        Chapter cap5 = Chapter.builder()
                                .name("O Sinal das Estrelas")
                                .content("""
                                    De um observatório esquecido em Marte, um cientista solitário detecta um padrão nas estrelas.
                                    Não era aleatório. Era uma linguagem.
                                    
                                    Quando decifrou os primeiros símbolos, entendeu: era uma promessa.
                                    Alguém — ou algo — estava tentando alcançá-los.
                                    
                                    E a mensagem dizia: "Não estão sozinhos."
                                    """)
                                .creationDate(Instant.now())
                                .build();

        Chapter cap6 = Chapter.builder()
                                .name("Sombras na Neve")
                                .content("""
                                    Os ecos dos ancestrais ressoavam sob cada pegada.
                                    A floresta gelada escondia não apenas o inimigo, mas segredos enterrados há séculos.
                                    
                                    Um mapa encontrado em uma caverna revelou o caminho para o Coração Congelado,
                                    onde a história do clã começou — e pode terminar.
                                    """)
                                .creationDate(Instant.now().plusSeconds(1))
                                .build();

        Chapter cap7 = Chapter.builder()
                                .name("Aliança Fria")
                                .content("""
                                    Clãs rivais se encontram sob uma bandeira improvisada.
                                    O frio era menos cruel do que o silêncio entre os líderes.
                                    
                                    Mas diante da ameaça iminente, até os juramentos de vingança precisaram congelar.
                                    A aliança foi selada com uma gota de sangue e um olhar gélido.
                                    """)
                                .creationDate(Instant.now().plusSeconds(2))
                                .build();

        Chapter cap8 = Chapter.builder()
                                .name("Eco do Passado")
                                .content("""
                                    Dentro da cripta glacial, as paredes contavam histórias esquecidas.
                                    Havia nomes, datas e desenhos que pareciam vivos à luz da tocha.
                                    
                                    Um dos guerreiros reconheceu o rosto de sua mãe em uma pintura milenar.
                                    O passado ecoava não apenas em palavras, mas no sangue.
                                    """)
                                .creationDate(Instant.now().plusSeconds(3))
                                .build();

        Chapter cap9 = Chapter.builder()
                                .name("A Última Geada")
                                .content("""
                                    O céu escureceu. A terra tremeu. 
                                    A criatura que despertou sob o lago congelado rugia como mil trovões.
                                    
                                    Guerreiros e magos, unidos pelo fim, se ergueram em uma última tentativa de resistir.
                                    
                                    A Última Geada caiu — não como neve, mas como gelo eterno sobre o mundo.
                                    """)
                                .creationDate(Instant.now().plusSeconds(4))
                                .build();


        cap1.setTale(tale1);
        cap2.setTale(tale1);
        cap3.setTale(tale2);
        cap4.setTale(tale3);
        cap5.setTale(tale4);
        cap6.setTale(tale2);
        cap7.setTale(tale2);
        cap8.setTale(tale2);
        cap9.setTale(tale2);

        chapterRepository.saveAll(
            Set.of(
                cap1,
                cap2,
                cap3,
                cap4,
                cap5,
                cap6,
                cap7,
                cap8,
                cap9
            )
        );

        user1.setLikes(new HashSet<>());

        user1.getLikes().add(tale2);

        userRepository.save(user1);

        user1.setFavorites(new HashSet<>());
        user2.setFavorites(new HashSet<>());
        user3.setFavorites(new HashSet<>());

        user1.getFavorites().add(tale2);
        user2.getFavorites().add(tale2);
        user3.getFavorites().add(tale2);

        userRepository.saveAll(
            Set.of(
                user1,
                user2,
                user3
            )
        );

    }
}
