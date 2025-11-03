package lotto.domain;

public enum LottoPrize {
  NO_PRIZE(0,0,0),
  FIFTH(5000, 3, 0),
  FOURTH(50000, 4, 0),
  THIRD(1500000, 5, 0),
  SECOND(30000000, 5, 1),
  FIRST(2000000000, 6, 0);

  private int price;
  private int match_hit;
  private int match_bonus;
  LottoPrize(int price, int match_hit, int match_bonus){
    this.price = price;
    this.match_hit = match_hit;
    this.match_bonus = match_bonus;
  }

  public int getPrice(){
    return price;
  }

  public static LottoPrize getLottoPrize(int match_hit, int match_bonus){
    if(match_hit == 6) return LottoPrize.FIRST;
    if(match_hit == 5){
      if(match_bonus == 1) return LottoPrize.SECOND;
      return LottoPrize.THIRD;
    }
    if(match_hit == 4) return LottoPrize.FOURTH;
    if(match_hit == 3) return LottoPrize.FIFTH;
    return LottoPrize.NO_PRIZE;
  }

  @Override
  public String toString(){
    if(match_bonus > 0){
      return String.format("%d개 일치, 보너스 볼 일치 (%,d원) - ", match_hit, price);
    }
    if(price != 0){
      return String.format("%d개 일치 (%,d원) - ", match_hit, price);
    }
    return "";
  }
}
