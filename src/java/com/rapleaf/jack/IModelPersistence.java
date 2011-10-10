//
// Copyright 2011 Rapleaf
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.rapleaf.jack;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.rapleaf.jack.test_project.database_1.models.User;


public interface IModelPersistence<T extends ModelWithId> extends Serializable {

  public interface RecordSelector<T extends ModelWithId> {
    public boolean selectRecord(T record);
  }
  
  /**
   * Update an existing T instance in the persistence.
   * @param model
   * @return
   * @throws IOException
   */
  public boolean save(T model) throws IOException;

  /**
   * Find the T instance with specified id, or null if there is no such instance.
   * @param id
   * @return
   * @throws IOException
   */
  public T find(int id) throws IOException;
  
  public Set<T> find(Set<Integer> ids) throws IOException;

  public Set<T> find(Map<Enum, Object> fieldsMap) throws IOException;

  public void clearCacheById(int id) throws IOException;

  public Set<T> findAllByForeignKey(String foreignKey, int id) throws IOException;
  
  public Set<T> findAllByForeignKey(String foreignKey, Set<Integer> ids) throws IOException;

  public void clearCacheByForeignKey(String foreignKey, int id);
  
  public void clearForeignKeyCache();

  /**
   * Effectively the same as delete(model.getId()).
   * @param model
   * @return
   * @throws IOException
   */
  public boolean delete(T model) throws IOException;

  /**
   * Destroy record with <i>id</i>.
   * @param id
   * @return
   * @throws IOException
   */
  public boolean delete(int id) throws IOException;

  /**
   * Delete all records in this persistence.
   * @return
   * @throws IOException
   */
  public boolean deleteAll() throws IOException;

  public Set<T> findAll() throws IOException;

  public Set<T> findAll(String conditions) throws IOException;
  
  public Set<T> findAll(String conditions, RecordSelector<T> selector) throws IOException;
  
  /**
   * Caching is on by default, and is toggled with enableCaching() and disableCaching().
   * 
   * While caching is disabled, the cache is neither read from nor written to.  However, 
   * disableCaching() does not clear the cache, so the cache contents are preserved for when
   * caching is enabled again.
   */
  public boolean isCaching();  

  public void enableCaching();

  public void disableCaching();
}
