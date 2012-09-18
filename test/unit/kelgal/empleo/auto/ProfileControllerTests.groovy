package kelgal.empleo.auto



import kelgal.empleo.auto.ProfileController;
import kelgal.empleos.Profile;

import org.junit.*
import grails.test.mixin.*

@TestFor(ProfileController)
@Mock(Profile)
class ProfileControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/profile/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.profileInstanceList.size() == 0
        assert model.profileInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.profileInstance != null
    }

    void testSave() {
        controller.save()

        assert model.profileInstance != null
        assert view == '/profile/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/profile/show/1'
        assert controller.flash.message != null
        assert Profile.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/profile/list'

        populateValidParams(params)
        def profile = new Profile(params)

        assert profile.save() != null

        params.id = profile.id

        def model = controller.show()

        assert model.profileInstance == profile
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/profile/list'

        populateValidParams(params)
        def profile = new Profile(params)

        assert profile.save() != null

        params.id = profile.id

        def model = controller.edit()

        assert model.profileInstance == profile
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/profile/list'

        response.reset()

        populateValidParams(params)
        def profile = new Profile(params)

        assert profile.save() != null

        // test invalid parameters in update
        params.id = profile.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/profile/edit"
        assert model.profileInstance != null

        profile.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/profile/show/$profile.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        profile.clearErrors()

        populateValidParams(params)
        params.id = profile.id
        params.version = -1
        controller.update()

        assert view == "/profile/edit"
        assert model.profileInstance != null
        assert model.profileInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/profile/list'

        response.reset()

        populateValidParams(params)
        def profile = new Profile(params)

        assert profile.save() != null
        assert Profile.count() == 1

        params.id = profile.id

        controller.delete()

        assert Profile.count() == 0
        assert Profile.get(profile.id) == null
        assert response.redirectedUrl == '/profile/list'
    }
}
