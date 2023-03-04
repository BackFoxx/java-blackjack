package controller;

import domain.BlackJack;
import domain.player.PlayerReadOnly;
import domain.strategy.RandomBasedIndexGenerator;
import view.Command;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackJackApplication {
    public void startGame() {
        BlackJack blackJack = new BlackJack(getParticipantNames(), new RandomBasedIndexGenerator());
        initializedBlackjackGame(blackJack);

        giveCardToPlayers(blackJack);
        blackJack.battle2();
        OutputView.printPlayersGameResults(blackJack.getGameResult());
    }

    private String getParticipantNames() {
        OutputView.printParticipantNamesGuide();
        return InputView.repeat(InputView::inputParticipantNames);
    }

    private void initializedBlackjackGame(BlackJack blackJack) {
        blackJack.giveTwoCardToPlayers();
        OutputView.printPlayersInformation(blackJack.getPlayers2());
    }

    private void giveCardToPlayers(BlackJack blackJack) {
        giveCardToParticipants(blackJack);
        giveCardToDealer(blackJack);
        OutputView.printPlayersFinalInformation(blackJack.getPlayers2());
    }

    private void giveCardToParticipants(BlackJack blackJack) {
        List<PlayerReadOnly> participants = blackJack.getParticipants();
        for (PlayerReadOnly participant : participants) {
            giveCardToParticipant(blackJack, participant);
        }
    }

    private void giveCardToParticipant(BlackJack blackJack, PlayerReadOnly participant) {
        Command command = getCommand(participant.getName());
        if (command.isYes()) {
            blackJack.giveCard(participant.getName());
            OutputView.printParticipantCardCondition(List.of(participant));
        }

        if (command.isNo() || participant.isBurst()) {
            stopGivingCard(participant, command);
            return;
        }
        giveCardToParticipant(blackJack, participant);
    }

    private Command getCommand(String participantName) {
        OutputView.printAddCardGuide(participantName);
        return InputView.repeat(InputView::inputAddCardCommand);
    }

    private void stopGivingCard(PlayerReadOnly participant, Command command) {
        if (command.isNo()) {
            OutputView.printParticipantCardCondition(List.of(participant));
            return;
        }

        if (participant.isBurst()) {
            OutputView.printBurstMessage(participant.getName());
        }
    }

    private void giveCardToDealer(BlackJack blackJack) {
        if (blackJack.shouldDealerGetCard()) {
            blackJack.giveDealerCard();
            OutputView.printGiveDealerCardMessage();
        }
    }
}
