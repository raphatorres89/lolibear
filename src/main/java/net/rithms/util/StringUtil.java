package net.rithms.util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.rithms.riot.dto.Static.ChampionSpell;
import net.rithms.riot.dto.Static.SpellVars;

public class StringUtil {

	private static Map<String, String> nomes;

	public static String arrumarTooltip(String s) {
		nomes = getNomes();
		try {
			for (String key : nomes.keySet()) {
				if (s.contains(key)) {
					s = s.replace(key, nomes.get(key));
				}
			}
		} catch (Exception e) {
		}
		return s;
	}

	public static String arrumarVar(String s, ChampionSpell spell) {
		List<SpellVars> feiticos = spell.getVars();
		try {
			for (SpellVars feitico : feiticos) {
				String expression = "{{ " + feitico.getKey() + " }}";
				String porEste = "";
				if (s.contains(expression)) {

					if (feitico.getCoeff().size() > 1) {

						porEste = feitico.getCoeff().get(0).toString();
						for (int i = 1; i < feitico.getCoeff().size(); i++) {
							porEste = porEste + "/" + feitico.getCoeff().get(i).toString();
						}
					} else {
						porEste = limparNumero(feitico.getCoeff().get(0) * 100);
					}
				}
				s = s.replace(expression, porEste + "% " + feitico.getLink());
			}
		} catch (Exception e) {
		}

		// eX
		try {
			for (int i = 0; i < spell.getEffectBurn().size(); i++) {
				String var = "{{ e" + i + " }}";
				if (s.contains(var)) {
					s = s.replace(var, spell.getEffectBurn().get(i));
				}
			}
		} catch (Exception e) {
		}

		// fX
		try {
			for (int i = 1; i < 10; i++) {
				String var = "{{ f" + i + " }}";
				if (s.contains(var)) {
					s = s.replace(var, "0");
				}
			}
		} catch (Exception e) {
		}

		// palavras
		try {
			for (int i = 0; i < spell.getVars().size(); i++) {
				String var = "{{ " + spell.getVars().get(i).getKey() + " }}";
				String tipo = spell.getVars().get(i).getLink();
				if (s.contains(s)) {
					String ss = limparNumero(spell.getVars().get(i).getCoeff().get(0) * 100);
					s = s.replace(var, ss + "% " + tipo);
				}
			}
		} catch (Exception e) {
		}
		if (s.contains("@cooldownchampion")) {
			s = s.replace("@cooldownchampion", "");
		}
		return s;
	}

	public static String limparNumero(Double d) {
		DecimalFormat format = new DecimalFormat("0");
		return format.format(d);
	}

	public static Map<String, String> getNomes() {
		nomes = new HashMap<String, String>();
		nomes.put("charabilitypower", new String(" Poder de habilidade"));
		nomes.put("charabilitypower2", new String(" Poder de habilidade"));
		nomes.put("spelldamage", new String(" Poder de habilidade"));
		nomes.put(" attackdamage", new String(" Dano físico"));
		nomes.put("bonusattackdamage", new String(" Dano físico adicional"));
		nomes.put("bonushealth", new String(" Vida adicional"));
		return nomes;
	}
}
