import MainHeader from '../../components/MainHeader/MainHeader'
import './MainPage.css'
import { Outlet } from 'react-router-dom';

const MainPage = () => {
    return (
        <div>
            <MainHeader />
            <div id="detail">
                <Outlet />
            </div>
        </div>
    );
}

export default MainPage;
