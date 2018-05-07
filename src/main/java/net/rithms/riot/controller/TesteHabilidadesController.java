package net.rithms.riot.controller;

import java.io.Serializable;
import java.text.DecimalFormat;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.staticdata.ChampData;
import net.rithms.riot.dto.Static.Champion;
import net.rithms.riot.dto.Static.ChampionSpell;

@ManagedBean(name="testeBean")
@ViewScoped
public class TesteHabilidadesController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Champion campeao;
	private RiotApi api = new RiotApi();
	private String[] botoesMagia = new String[]{"Q","W","E","R"};
	
	@PostConstruct
	public void init() {
		/*this.render = false;*/
		this.campeao = new Champion();
		try {
			this.campeao = api.getDataChampion(14, null, null, ChampData.ALL);
		} catch (RiotApiException e) {
			e.printStackTrace();
		}
	}
	
	public String limparNumero(Double d) {
		DecimalFormat format = new DecimalFormat("0");
		return format.format(d);
	}
	
	public String preencherVariaveisDoFeitico(ChampionSpell spell) {
		String s = spell.getTooltip();
		// AP
		try {
			for(int i = 0; i < spell.getVars().size(); i++) {
				String var = "{{ " + spell.getVars().get(i).getKey() + " }}";
				String tipo = spell.getVars().get(i).getLink();
				if (s.contains(s)) {
					String ss = limparNumero(spell.getVars().get(i).getCoeff().get(0) * 100);
					s = s.replace(var, ss + "% " + tipo);
				}
			}
		} catch (Exception e) {
			/*e.printStackTrace();*/
		}
		
		// AD
		try {
			for(int i = 0; i < spell.getEffectBurn().size(); i++) {
				String var = "{{ e" + i + " }}";
				if (s.contains(var)) {
					s = s.replace(var, spell.getEffectBurn().get(i));
				}
			}
		} catch (Exception e) {
			/*e.printStackTrace();*/
		}
		
		// F?
		try {
			for(int i = 1; i < 10; i++) {
				String var = "{{ f" + i + " }}";
				if (s.contains(var)) {
					s = s.replace(var, "0");
				}
			}
		} catch (Exception e) {
			/*e.printStackTrace();*/
		}
		System.out.println(s);
		return s;
	}
	
	
	public char chave(String c) {
		return c.charAt(c.length() - 1);
	}
	
	public Champion getCampeao() {
		return campeao;
	}

	public void setCampeao(Champion campeao) {
		this.campeao = campeao;
	}

	public String[] getBotoesMagia() {
		return botoesMagia;
	}
	
}
