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

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "PIG_FILE_OBJECT_STORAGE")
public class PigFileObjectStorage implements Serializable {

    @Serial
    private static final long serialVersionUID = 37568565656844L;

    private Long idPigFileObjectStorage;
    // private Long idPigFileObject;
    private Long idDecBackend;
    private String nmTenant;
    private String nmBucket;
    private String cdKeyFile;
    private PigFileObject pigFileObject;

    public PigFileObjectStorage() {
        super();
    }

    @Id
    @Column(name = "ID_PIG_FILE_OBJECT_STORAGE")
    public Long getIdPigFileObjectStorage() {
        return idPigFileObjectStorage;
    }

    public void setIdPigFileObjectStorage(Long idPigFileObjectStorage) {
        this.idPigFileObjectStorage = idPigFileObjectStorage;
    }

    @Column(name = "ID_DEC_BACKEND")
    public Long getIdDecBackend() {
        return idDecBackend;
    }

    public void setIdDecBackend(Long idDecBackend) {
        this.idDecBackend = idDecBackend;
    }

    @Column(name = "NM_TENANT")
    public String getNmTenant() {
        return nmTenant;
    }

    public void setNmTenant(String nmTenant) {
        this.nmTenant = nmTenant;
    }

    @Column(name = "NM_BUCKET")
    public String getNmBucket() {
        return nmBucket;
    }

    public void setNmBucket(String nmBucket) {
        this.nmBucket = nmBucket;
    }

    @Column(name = "CD_KEY_FILE")
    public String getCdKeyFile() {
        return cdKeyFile;
    }

    public void setCdKeyFile(String cdKeyFile) {
        this.cdKeyFile = cdKeyFile;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PIG_FILE_OBJECT")
    public PigFileObject getPigFileObject() {
        return pigFileObject;
    }

    public void setPigFileObject(PigFileObject pigFileObject) {
        this.pigFileObject = pigFileObject;
    }
}