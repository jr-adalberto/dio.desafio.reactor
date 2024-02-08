package Desafio.Reactor.DIO;

import reactor.core.publisher.Mono;

public class Question3 {

    public Mono<Void> userIsValid(final User user) {
        if (user.password().length() > 8) {
            return Mono.empty();
        } else {
            return Mono.error(new IllegalArgumentException("Usuário Inválido!"));
        }
    }

    public static void main(String[] args) {
        Question3 userValidator = new Question3();

        User validUser = new User(1L, "Harry Potter", "harryp@example.com", "validpassword123", true);
        User invalidUser = new User(2L, "Gina  Weasley", "ginaw@example.com", "loveharry123", false);

        Mono<Void> validMono = userValidator.userIsValid(validUser);
        Mono<Void> invalidMono = userValidator.userIsValid(invalidUser);

        validMono.subscribe(
                value -> System.out.println("Usuário válido!"),
                error -> System.err.println("Erro: " + error),
                () -> System.out.println("Validação completa!")
        );

        invalidMono.subscribe(
                value -> System.out.println("Usuário inválido!"),
                error -> System.err.println("Erro: " + error),
                () -> System.out.println("Validação completa!")
        );
    }

}