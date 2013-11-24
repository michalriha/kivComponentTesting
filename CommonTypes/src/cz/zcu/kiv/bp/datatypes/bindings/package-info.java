/**
 * Implements common types for UniPlayer and UniMocker binding packages
 */
@XmlSchema(
	xmlns = {
		@XmlNs(
			prefix = cz.zcu.kiv.bp.namespaces.DataTypes.SCENARIO_PREFIX,
			namespaceURI = cz.zcu.kiv.bp.namespaces.DataTypes.SCENARIO_URI
		)
	},
	namespace = cz.zcu.kiv.bp.namespaces.DataTypes.SCENARIO_URI,
	location = cz.zcu.kiv.bp.namespaces.DataTypes.SCENARIO_SCHEMA,
	elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED
)
package cz.zcu.kiv.bp.datatypes.bindings;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlSchema;