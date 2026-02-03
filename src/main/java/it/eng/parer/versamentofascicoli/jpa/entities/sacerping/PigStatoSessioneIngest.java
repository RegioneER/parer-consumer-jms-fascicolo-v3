/*
 * Engineering Ingegneria Informatica S.p.A.
 *
 * Copyright (C) 2023 Regione Emilia-Romagna <p/> This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version. <p/> This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Affero General Public License for more details. <p/> You should
 * have received a copy of the GNU Affero General Public License along with this program. If not,
 * see <https://www.gnu.org/licenses/>.
 */

package it.eng.parer.versamentofascicoli.jpa.entities.sacerping;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import it.eng.parer.versamentofascicoli.jpa.sequence.NonMonotonicSequence;
import jakarta.persistence.*;

/**
 * The persistent class for the PIG_STATO_SESSIONE_INGEST database table.
 */
@Entity
@Table(name = "PIG_STATO_SESSIONE_INGEST")
public class PigStatoSessioneIngest implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idStatoSessioneIngest;
    private String tiStato;
    private LocalDateTime tsRegStato;
    private PigSessioneIngest pigSessioneIngest;
    private Long idVers;

    public PigStatoSessioneIngest() {
        // non usato
    }

    @Id
    @NonMonotonicSequence(sequenceName = "SPIG_STATO_SESSIONE_INGEST", incrementBy = 1)
    @Column(name = "ID_STATO_SESSIONE_INGEST")
    public Long getIdStatoSessioneIngest() {
        return this.idStatoSessioneIngest;
    }

    public void setIdStatoSessioneIngest(Long idStatoSessioneIngest) {
        this.idStatoSessioneIngest = idStatoSessioneIngest;
    }

    @Column(name = "TI_STATO")
    public String getTiStato() {
        return this.tiStato;
    }

    public void setTiStato(String tiStato) {
        this.tiStato = tiStato;
    }

    @Column(name = "TS_REG_STATO")
    public LocalDateTime getTsRegStato() {
        return this.tsRegStato;
    }

    public void setTsRegStato(LocalDateTime tsRegStato) {
        this.tsRegStato = tsRegStato;
    }

    // bi-directional many-to-one association to PigSessioneIngest
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SESSIONE_INGEST")
    public PigSessioneIngest getPigSessioneIngest() {
        return this.pigSessioneIngest;
    }

    public void setPigSessioneIngest(PigSessioneIngest pigSessioneIngest) {
        this.pigSessioneIngest = pigSessioneIngest;
    }

    // usata solo come chiave di partizionamento, non voglio la join con PigVers
    @Column(name = "ID_VERS")
    public Long getIdVers() {
        return this.idVers;
    }

    public void setIdVers(Long idVers) {
        this.idVers = idVers;
    }

}
