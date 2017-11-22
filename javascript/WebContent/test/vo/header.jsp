<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <div class="st-header">
          <div class="fluid-cols">
            <div class="min-col">
              <div class="st-header__menu">
                <button class="btn st-sidebar__toggle"><i class="fa fa-bars"></i></button>
              </div>
            </div>
            <div class="expand-col text-ellipsis">
              <div class="st-header__title"><span>Blank Page</span>
              </div>
            </div>
            <div class="min-col">
              <ul class="st-header__actions text-nowrap">
                <li class="hidden-xs st-header__act st-spanel__open st-header__notifications" data-tab="#notifications"><i class="fa fa-bell"></i>
                  <div class="st-header__count st-notifications-count"></div>
                </li>
                <li class="hidden-xs st-header__act st-spanel__open st-header__settings" data-tab="#settings"><i class="fa fa-cog"></i></li>
                <li class="st-header__act st-header__user" data-toggle="popup" data-target="#user-popup"><i class="fa fa-user"></i>
					
					<%@ include file="mypopup.jsp" %>

                </li>
              </ul>
            </div>
          </div>
        </div>