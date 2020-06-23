//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.village.quest;

import net.minecraft.entity.player.EntityPlayer;

public interface IQuestProvider {

    void createNewQuest();

    Quest getCurrentQuest();

    void removeCurrentQuest();

    void completeCurrentQuest(EntityPlayer player);

    int getCurrentQuestTicksLeft();
}
