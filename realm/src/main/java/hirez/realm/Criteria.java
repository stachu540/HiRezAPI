package hirez.realm;

public enum Criteria {
    Team_Wins(1),
    Team_Average_Placement(2),
    Individual_Average_Kills(3),
    Winrate(4);

    final Integer id;

    Criteria(int id) {
        this.id = id;
    }
}
