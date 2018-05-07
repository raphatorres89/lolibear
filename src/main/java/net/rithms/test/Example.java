package net.rithms.test;
import java.util.List;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.staticdata.ChampData;
import net.rithms.riot.dto.Static.Champion;
import net.rithms.util.CampeaoUtil;

public class Example {

	public static void main(String[] args) throws RiotApiException {
		
		RiotApi api = new RiotApi("RGAPI-30065780-72b1-4635-bd93-0ad8c4677168");

		List<Champion> campeoes = CampeaoUtil.listarCampeoes(api.getDataChampionList());
		
		for (Champion c: campeoes) {
			System.out.println(c.getName());
		}
		
		Champion c = api.getDataChampion(19,null,null,ChampData.ALL);
		System.out.println(c);
	}
	
	

}