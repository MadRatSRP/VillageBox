//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.config.jsonData;

public class JsonQuest {

    //objectives & rewards item stack string format:
    //domain,itemID,amount,meta
    //example: minecraft,log,1,0 indicates a Oak Wood from Vanillia minecraft
    //the amount should be between 1 and 64

    public String[] objectives;
    public String[] rewards;

    public JsonQuest(String[] objectives, String[] rewards) {
        this.objectives = objectives;
        this.rewards = rewards;
    }
}
