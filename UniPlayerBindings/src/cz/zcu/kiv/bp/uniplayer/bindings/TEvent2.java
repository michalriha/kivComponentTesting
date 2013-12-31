package cz.zcu.kiv.bp.uniplayer.bindings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.osgi.service.event.Event;

import cz.zcu.kiv.bp.uniplayer.bindings.adapted.Event2Property;
import cz.zcu.kiv.bp.uniplayer.bindings.adapted.Event2PropertyAdapter;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "TEvent2", propOrder = {
	"topic",
	"eventProperties"
})
public class TEvent2
{
	protected String topic;
	
	protected List<Event2Property> eventProperties;

	protected Event cache = null;

	@XmlAttribute(name= "topic")
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	@XmlElement(name = "property", type = TEvent2Property.class)
	@XmlJavaTypeAdapter(Event2PropertyAdapter.class)
	public List<Event2Property> getEventProperties() {
		if (eventProperties == null) {
			eventProperties = new ArrayList<>();
		}
		return eventProperties;
	}

	public void setEventProperties(List<Event2Property> eventProperties) {
		this.eventProperties = eventProperties;
	}
	
	public Event toEvent()
	{
		if (cache == null)
		{
			Map<String, Object> props = new HashMap<>();
			for (Event2Property property : eventProperties)
			{
				try
				{
					props.put(property.getKey(), property.getValue());
				}
				catch (Exception ignored) {}
			}
			cache = new Event(topic, props);
		}
		
		return cache;
	}
}
