//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.village.trading;

public interface ITrading {
    TradingRecipeList getTradingRecipeList();

    void onTrade();
}
