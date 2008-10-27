/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2008, Red Hat Middleware LLC or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Middleware LLC.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.jboss.envers.query.criteria;

import java.util.ArrayList;
import java.util.List;

import org.jboss.envers.configuration.VersionsConfiguration;
import org.jboss.envers.tools.query.Parameters;
import org.jboss.envers.tools.query.QueryBuilder;

/**
 * @author Adam Warski (adam at warski dot org)
 */
public class VersionsDisjunction implements VersionsCriterion, ExtendableCriterion {
    private List<VersionsCriterion> criterions;

    public VersionsDisjunction() {
        criterions = new ArrayList<VersionsCriterion>();
    }

    public VersionsDisjunction add(VersionsCriterion criterion) {
        criterions.add(criterion);
        return this;
    }

    public void addToQuery(VersionsConfiguration verCfg, String entityName, QueryBuilder qb, Parameters parameters) {
        Parameters orParameters = parameters.addSubParameters(Parameters.OR);

        for (VersionsCriterion criterion : criterions) {
            criterion.addToQuery(verCfg, entityName, qb, orParameters);
        }
    }
}