package cz.zcu.kiv.bp.uniplayer.bindings.adapted;

import java.util.Arrays;

public class Event2Property extends Value
{
	protected String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	@Override
	public String toString()
	{
		String valueStr = "";
		if (this.getValue() == null) valueStr = "null";
		else if (this.getType().isArray()) valueStr = Arrays.deepToString((Object[]) this.getValue());
		else valueStr = this.getValue().toString();
		
		return String.format(
			"key: %s => value: %s (described type: %s, actual type: %s)",
			this.getKey(),
			valueStr,
			this.getType().getCanonicalName(),
			this.getValue() != null ? this.getValue().getClass().getCanonicalName() : "void"
		);
	}
}
