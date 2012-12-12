package eu.hornerproject.dandelion

import grails.test.mixin.*
import eu.hornerproject.dandelion.MetasetTypeController
import server.MetasetType

@TestFor(MetasetTypeController)
@Mock(MetasetType)
class MetasetTypeControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/metasetType/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.metasetTypeInstanceList.size() == 0
        assert model.metasetTypeInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.metasetTypeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.metasetTypeInstance != null
        assert view == '/metasetType/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/metasetType/show/1'
        assert controller.flash.message != null
        assert MetasetType.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/metasetType/list'


        populateValidParams(params)
        def metasetType = new MetasetType(params)

        assert metasetType.save() != null

        params.id = metasetType.id

        def model = controller.show()

        assert model.metasetTypeInstance == metasetType
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/metasetType/list'


        populateValidParams(params)
        def metasetType = new MetasetType(params)

        assert metasetType.save() != null

        params.id = metasetType.id

        def model = controller.edit()

        assert model.metasetTypeInstance == metasetType
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/metasetType/list'

        response.reset()


        populateValidParams(params)
        def metasetType = new MetasetType(params)

        assert metasetType.save() != null

        // test invalid parameters in update
        params.id = metasetType.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/metasetType/edit"
        assert model.metasetTypeInstance != null

        metasetType.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/metasetType/show/$metasetType.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        metasetType.clearErrors()

        populateValidParams(params)
        params.id = metasetType.id
        params.version = -1
        controller.update()

        assert view == "/metasetType/edit"
        assert model.metasetTypeInstance != null
        assert model.metasetTypeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/metasetType/list'

        response.reset()

        populateValidParams(params)
        def metasetType = new MetasetType(params)

        assert metasetType.save() != null
        assert MetasetType.count() == 1

        params.id = metasetType.id

        controller.delete()

        assert MetasetType.count() == 0
        assert MetasetType.get(metasetType.id) == null
        assert response.redirectedUrl == '/metasetType/list'
    }
}
